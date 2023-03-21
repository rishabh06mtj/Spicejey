package com.ecom.spicejet.framework;

import java.util.LinkedHashSet;

public class CountDuplicate {

	public static void main(String[] args) {
		
		String s="Mandya";
		String stt = s.toLowerCase();
		char[] st = stt.toCharArray();
		LinkedHashSet<Character> hs=new LinkedHashSet<Character>();
		for(int i=0;i<st.length;i++)
		{
			hs.add(st[i]);
		}
		
		for(char hsr:hs)
		{
			int count=0;
			
			
				if(hsr=='a' ||hsr=='e' ||hsr=='i' ||hsr=='o' ||hsr=='u')
				{
					count++;
				}
			if(count==1) {
			System.out.println(hsr+" "+count);
			}
			
		}

	}

}
