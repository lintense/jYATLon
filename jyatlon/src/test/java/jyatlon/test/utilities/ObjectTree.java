package jyatlon.test.utilities;

/*
 * BSD 3-Clause Clear License
 * 
 * Copyright (c) 2019 Sylvain Nadeau
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted (subject to the limitations in the disclaimer 
 * below) provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of [Owner Organization] nor the names of its contributors 
 *    may be used to endorse or promote products derived from this software 
 *    without specific prior written permission.
 * 
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY 
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT 
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ObjectTree {
	private static final String INDENTATION = "    ";
	
	public static void dumpObject(Object o, PrintStream out) {
		dumpObject(o, new HashMap<Object,Integer>(), "", out);
	}
	private static Comparator<Field> getFieldComparator(){
		return new Comparator<Field>() {
			public int compare(Field a, Field b) 
		    { 
		        return a.getName().compareTo(b.getName()); 
		    } 
		};
	}
	private static void dumpObject(Object o, Map<Object, Integer> done, String indent, PrintStream out){

		if (o == null)
			out.println("null;");
		else if (o instanceof String)
			out.println("(" + o.getClass().getSimpleName() + ")\"" + o.toString() + "\";");
		else if (!isCollection(o) && !o.toString().contains(o.getClass().getSimpleName()))
			out.println("(" + o.getClass().getSimpleName() + ")" + o.toString() + ";");
		else if (done.containsKey(o))
			out.println(done.get(o) + ";");
		else {
			int id = done.size();
			done.put(o, id);
			
			out.println(formatName(o, id) + ";");
			indent += INDENTATION;

			// Sort Field by name
			Map<Field,Object> m = extractFields(o, done);
			SortedSet<Field> sortedSet = new TreeSet<Field>(getFieldComparator());
			sortedSet.addAll(m.keySet());
			showSizeIfNeeded(o, sortedSet, indent, out);
			for (Field k : sortedSet) {
				Object v = m.get(k);
				out.print(indent + k.getName() + ": ");
				if (v == null){
					dumpObject(v, done, indent, out);
				} else if (v instanceof Map){
					dumpObject(v, done, indent, out);
					showMap((Map<?,?>)v, done, indent, out);
				} else if (v instanceof Collection){
					dumpObject(v, done, indent, out);
					showCollection((Collection<?>)v, done, indent, out);
				} else {
					dumpObject(v, done, indent, out);
				}
			}
		}
	}
	/**
	 * @param o
	 * @param sortedSet
	 * @param indent
	 * @param out
	 * Some versions of Java have a useful size field for Collection that we want to keep!
	 */
	private static void showSizeIfNeeded(Object o, SortedSet<Field> sortedSet, String indent, PrintStream out) {
		if (o instanceof Collection && !sortedSet.stream().anyMatch(p->p.getName().equals("size")))
			out.println(indent + "size: (Integer)" + ((Collection<?>)o).size() + ";");
	}
	private static boolean isCollection(Object o) {
		Class<?> c = o.getClass();
		return c.isArray() ||  Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
	}
	private static void showCollection(Collection<?> l, Map<Object, Integer> done, String indent, PrintStream out) {
		indent += INDENTATION;
		int index = 0;
		for (Object o : l){
			out.print(indent + index++ + ": ");
			dumpObject(o, done, indent, out);
		}
	}
	private static void showMap(Map<?,?> m, Map<Object, Integer> done, String indent, PrintStream out) {
		indent += INDENTATION;
		for (Map.Entry<?,?> entry : m.entrySet()){
			Object v = entry.getValue();
			Object k = entry.getKey();
			out.print(indent + k + ": ");
			dumpObject(v, done, indent, out);
		}
	}
	private static String formatName(Object o, int id){
		if (o == null)
			return "null";
		return o.getClass().getSimpleName() + "@" + id;
	}
	
	/**
	 * @param re, the element to extract the fields from
	 * @return a handy map containing only the values of interest
	 */
	private static Map<Field,Object> extractFields(Object re, Map<Object, Integer> done){
		
		Map<Field,Object> result = new LinkedHashMap<Field, Object>();
		// Only keep the none transient fields
		Class<?> type = re.getClass();
		ArrayList<Field> fields = new ArrayList<Field>();
		while (type != null){
			fields.addAll(Arrays.asList(type.getDeclaredFields()));
			type = type.getSuperclass();
		}
		ArrayList<Field> filteredFields = new ArrayList<Field>();
		for (Field field : fields)
			if (!field.getName().equals("this$0") && !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
				filteredFields.add(field);
		if (filteredFields.size() == 1 && filteredFields.get(0).getName().equals("value"))
			return Collections.emptyMap();
		for (Field field : filteredFields){
			try {
				field.setAccessible(true);
				Object content = field.get(re);
				if (!done.containsKey(content)) // Watch out: all empty maps are considered equals!!!
					result.put(field, field.get(re));
				else 
					result.put(field, formatName(content, done.get(content)));
			} catch (Exception e) {
				throw new IllegalStateException("Cannot extract field value - Must be tested under Java version 8", e);
			}
		}
		return result;
	}
	/*
	 * https://stackoverflow.com/questions/50251798/what-is-an-illegal-reflective-access
	 * https://docs.oracle.com/javase/9/docs/api/java/lang/reflect/AccessibleObject.html#setAccessible-boolean-
	 */
}
