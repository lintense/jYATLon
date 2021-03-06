package jyatlon.core;

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

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lintense
 * SRP: Encapsulates all the path mechanisms
 */
public class ValuePath {

	// This is a naive implementation
	public final ValuePath parent;
	public final String[] aliases;
	public final String[] classes;
	public final Object[] objects;
	
	public static ValuePath getRoot(Object o) {
		return new ValuePath(Constant.ROOT, null, o);
	}
	public ValuePath(String pathName, String pathAlias, Object obj) {
		this.parent = null;
		this.aliases = new String[] {pathAlias};
		this.classes = new String[] {pathName};
		this.objects = new Object[] {obj};
	}
	protected ValuePath(ValuePath parent, String[] classes, String[] aliases, Object[] objects) {
		assert classes.length == aliases.length && aliases.length == objects.length;
		// Classes and objects arrays must match
		//assert IntStream.range(1, objects.length).filter(i -> objects[i] == null || objects[i].getClass().getSimpleName().equals(classes[i])).count() == objects.length - 1;
		// Alias names and classes names should not intersect
		assert !Stream.of(classes).filter(c -> new HashSet<>(Arrays.asList(aliases)).contains(c)).findAny().isPresent();
		
		this.parent = parent;
		this.aliases = aliases;
		this.classes = classes;
		this.objects = objects;
	}
	public boolean isRoot() { // Root assigned to class so it can be redefined
		return aliases.length == 1 && Constant.ROOT.equals(classes[0]) && aliases[0] == null;
	}
	public ValuePath add(String className, String pathAlias, Object obj) {
		assert className != null; // && pathAlias != null;
		return new ValuePath(this,
				Stream.of(Arrays.asList(this.classes), Arrays.asList(className)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.classes.length + 1]),
				Stream.of(Arrays.asList(this.aliases), Arrays.asList(pathAlias)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.aliases.length + 1]),
				Stream.of(Arrays.asList(this.objects), Arrays.asList(obj)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new Object[this.objects.length + 1])
			);
	}
	public ValuePath add(ValuePath p) {
		return new ValuePath(this,
				Stream.of(Arrays.asList(this.classes), Arrays.asList(p.classes)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.classes.length + p.classes.length]),
				Stream.of(Arrays.asList(this.aliases), Arrays.asList(p.aliases)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.aliases.length + p.aliases.length]),
				Stream.of(Arrays.asList(this.objects), Arrays.asList(p.objects)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new Object[this.objects.length + p.objects.length])
			);
	}
	public Object getObject() {
		return objects[objects.length - 1];
	}
//	public Object getObjectForName(String name) {
//		for (int i = classes.length; i > 0; i--)
//			if (name.equals(classes[i-1]) || name.equals(aliases[i-1]))
//				return objects[i-1];
//		throw new IllegalArgumentException("The name '" + name + "' cannot be found in the path");
//	}
//	public Object getObjectForName(String name, Object defaultObject) {
//		for (int i = classes.length; i > 0; i--)
//			if (name.equals(classes[i-1]) || name.equals(aliases[i-1]))
//				return objects[i-1];
//		return defaultObject;
//	}
	public String getAliasName() {
		return aliases[aliases.length - 1];
	}
	public String getClassName() {
		return classes[classes.length - 1];
	}
	public boolean containsClassName(String className) {
		return Arrays.stream(this.classes).anyMatch(x -> className.equals(x));
	}
	public boolean containsAliasName(String alias) {
		return Arrays.stream(this.aliases).anyMatch(x -> alias.equals(x));
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < classes.length; i++)
			sb.append(".").append(classes[i]).append(aliases[i] != null && !aliases[i].isEmpty() ? ":" + aliases[i] : "");
		return Constant.LVALUE + sb.toString().substring(1) + Constant.RVALUE;
	}
//	public boolean isSamePath(ValuePath vp) {
//		int len;
//		if ((len = this.length()) == vp.length()) {
//			for (int i = 0; i < len; i++)
//				if (!this.classes[i].equals(vp.classes[i])
//					|| (this.aliases[i] == null && vp.aliases[i] != null)
//					|| (this.aliases[i] != null && !this.aliases[i].equals(vp.aliases[i])))
//						return false;
//			return true;
//		}
//		return false;
//	}
	public int length() {
		return classes.length;
	}
}
