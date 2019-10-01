package model;

import java.math.BigInteger;

public class ConversionUtilities 
{
	public static String convertToBinary(BigInteger decimal)
	{
		String binary =  Integer.toString(decimal.intValueExact(),2);
		final int maxBinary = 8; 
		String returnedBinary = "";
		//adds an extra bit at the front if it's not an even number to split it
		if(binary.length() != maxBinary)
		{
			int difference = maxBinary - binary.length();
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

}
