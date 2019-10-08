package model;

import java.math.BigInteger;

public class ConversionUtilities 
{
	private static String maxBinary = "";
	public static String convertToBinary(BigInteger decimal)
	{
		String binary =  Integer.toString(decimal.intValueExact(),2);
		String returnedBinary = "";
		//adds an extra bit at the front if it's not an even number to split it
		if(binary.length() != maxBinary.length())
		{
			int difference = maxBinary.length() - binary.length();
			String appendingZero = "";

			for(int i = 0; i < difference; i++)
			{
				returnedBinary += "0";
			}
			returnedBinary += binary; 
		}
		else
		{
			returnedBinary = binary;
		}
		
		return returnedBinary;
	} 
	
	public static int convertToDecimal(String binary)
	{
		return Integer.parseInt(binary,2);
	}
	
	public static String convertToBinaryInitial(BigInteger decimal)
	{
		String binary =  Integer.toString(decimal.intValueExact(),2);
		int maxBits = binary.length() * 2;
		int count = 0;
		while(count < maxBits)
		{
			maxBinary += "0";
			count++;
		}
		return maxBinary;
		
	}

}
