package com.licence.emaillicence.util;

import java.util.Random;
import java.util.Scanner;

public class Util {
	
	
	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
		 String key ="";
		 int i,k,j=0,sub;
		 Random random = new Random();
		 char[] charachters= {'A','B','C','D','E','F','G','H','I','J','K','L','N','O','P','Q','R','S','T','U','V','W','X','Z','0','1','2','3','4','5','6'};
		 System.out.println("How many key want to print");
		 int keys = scanner.nextInt();
		 do {
			 key="";
			 i=0;
			 do {
				 k=0;
				 do {
					 sub= random.nextInt(charachters.length);
					 k++;
					 key+=charachters[sub];
				 } while(k < 8); 
					if(i < 3) key +="-"; 
				i++;
			 }while(i < 4); 
			 System.out.println(key);
			 j++;
		 }while(j < keys); 
		
	}

}
