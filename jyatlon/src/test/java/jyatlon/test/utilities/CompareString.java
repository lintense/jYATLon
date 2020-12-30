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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CompareString {

	public static boolean compareFileToString(File f, String s) throws IOException { // File is the reference
		String[] sl = getLines(s);
		String[] fl = getLines(f);
		long[] sv = linesToVect(sl);
		long[] fv = linesToVect(fl);
		long[] dv = lcs(fv, sv);
		int iref = 0, is = 0, id = 0;
		boolean result = true;
		
		while (iref < fv.length || is < sv.length) {
			if (id < dv.length && fv[iref] == dv[id] && sv[is] == dv[id]) { // Everything is equal
//				System.out.println(sl[is]);
				iref++; is++; id++;
			} else if ((id < dv.length && sv[is] != dv[id]) || (id >= dv.length && is < sv.length)){ // Missing line in s
				System.out.println("Extra String " + (is + 1) + ": " + sl[is++]);
				result = false;
			} else if ((id < dv.length && fv[iref] != dv[id]) || (id >= dv.length && iref < fv.length)){ // Missing line in ref
				System.out.println("Missing File " + (iref + 1) + ": " + fl[iref++]);
				result = false;
			} else
				throw new IllegalStateException("Error!");
		}
		return result;
	}
	public static int[] diff(long[] delta, long[] ref, long[] b) {
		int[] result = new int[delta.length];
		int iref = 0, ib = 0, idelta = 0;
		for (int i = 0; i < idelta; i++)
			result[idelta] = (ref[iref] == delta[idelta] ? iref/iref++ : 0) - (b[ib] == delta[idelta++] ? ib/ib++ : 0);
		return result;
	}
	public static String[] getLines(File file) throws IOException {
		return getLines(new BufferedReader(new FileReader(file)));
	}
	public static String[] getLines(String st) throws IOException {
		return getLines(new BufferedReader(new StringReader(st)));
	}
	public static String[] getLines(BufferedReader reader) throws IOException
	{
		List<String> result = new ArrayList<String>();
		try {
			String line = reader.readLine();
			while (line != null) {
				result.add(line);
				line = reader.readLine();
			}
		} finally {
			reader.close();
		}
		return result.toArray(new String[result.size()]);
	}
	public static long[] linesToVect(String[] lines) {
		long[] result = new long[lines.length];
		IntStream.range(0, lines.length).forEach(i -> result[i] = getSum(lines[i]));
		return result;
	}
	public static long getSum(String s) {
		byte[] array = s.getBytes();
		return LongStream.range(0, array.length).map(i -> array[(int)i]).reduce(0l, Long::sum);
	}
	public static String lcs(String a, String b)
	{
	    int[][] lengths = new int[a.length()+1][b.length()+1];
	 
	    // row 0 and column 0 are initialized to 0 already
	 
	    for (int i = 0; i < a.length(); i++)
	        for (int j = 0; j < b.length(); j++)
	            if (a.charAt(i) == b.charAt(j))
	                lengths[i+1][j+1] = lengths[i][j] + 1;
	            else
	                lengths[i+1][j+1] =
	                    Math.max(lengths[i+1][j], lengths[i][j+1]);
	 
	    // read the substring out from the matrix
	    StringBuilder sb = new StringBuilder();
	    for (int x = a.length(), y = b.length();
	         x != 0 && y != 0; ) {
	        if (lengths[x][y] == lengths[x-1][y])
	            x--;
	        else if (lengths[x][y] == lengths[x][y-1])
	            y--;
	        else {
	            assert a.charAt(x-1) == b.charAt(y-1);
	            sb.append(a.charAt(x-1));
	            x--;
	            y--;
	        }
	    }
	 
	    return sb.reverse().toString();
	}
	public static long[] lcs(long[] a, long[] b) {
	    int[][] lengths = new int[a.length+1][b.length+1];
	 
	    // row 0 and column 0 are initialized to 0 already
	    for (int i = 0; i < a.length; i++)
	        for (int j = 0; j < b.length; j++)
	            if (a[i] == b[j])
	                lengths[i+1][j+1] = lengths[i][j] + 1;
	            else
	                lengths[i+1][j+1] =
	                    Math.max(lengths[i+1][j], lengths[i][j+1]);
	 
	    // read the substring out from the matrix
	    List<Long> sb = new Vector<Long>();
	    for (int x = a.length, y = b.length;
	         x != 0 && y != 0; ) {
	        if (lengths[x][y] == lengths[x-1][y])
	            x--;
	        else if (lengths[x][y] == lengths[x][y-1])
	            y--;
	        else {
	            assert a[x-1] == b[y-1];
	            sb.add(a[x-1]);
	            x--;
	            y--;
	        }
	    }
	 
	    Collections.reverse(sb);
	    long[] result = new long[sb.size()];
	    int index = 0;
	    for (Long x : sb)
	    	result[index++] = x;
	    return result;
	}

}
