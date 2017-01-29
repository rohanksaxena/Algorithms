package com.rks.algorithms;

import java.math.BigInteger;
public class RecursiveMultiplication {

		public static void main(String[] args) {
			String n1 = new String("3141592653589793238462643383279502884197169399375105820974944592");
			String n2 = new String("2718281828459045235360287471352662497757247093699959574966967627");
			System.out.println("The product is : " + RecursiveMultiply(n1,n2));	
	}

	public static String RecursiveMultiply(String n1, String n2){
		BigInteger x = new BigInteger(n1);
		BigInteger y = new BigInteger(n2);
		if ((n1.length()==1)&&(n2.length()==1))
			return String.valueOf(x.multiply(y));
		else{
			String a,b,c,d;
			BigInteger c1,c2,c3,c4,c5,t1,t2,result;
			a = n1.substring(0,n1.length()/2);
			b = n1.substring(n1.length()/2);
			c = n2.substring(0,n2.length()/2);
			d = n2.substring(n2.length()/2);
			c1 = new BigInteger(RecursiveMultiply(a,c));
			c2 = new BigInteger(RecursiveMultiply(a,d));
			c3 = new BigInteger(RecursiveMultiply(b,c));
			c4 = new BigInteger(RecursiveMultiply(b,d));
			c5 = c2.add(c3);
			t1 = c1.multiply((new BigInteger(Pad("1",n1.length()))));
			t2 = c5.multiply((new BigInteger(Pad("1",n1.length()/2))));
			result = t1.add(t2.add(c4));
			return String.valueOf(result);
			} 
	}

	public static String Pad(String str, int n){
		StringBuffer string = new StringBuffer(str);
		for(int i=0;i<n;i++){
			string.append("0");
		}
		return string.toString();
	}

}
