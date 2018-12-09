package com.alesto.legaladviser.model;

import java.util.ArrayList;
import java.util.List;

public enum Category {
	CRIME,DEBT,HOUSING,IMMIGRATION,WELLFARE;
	
	public int getOrdinal()
	{
		return this.ordinal();
	}
	
	//Bitwise converter categories to bitmap
    public static byte convertToByte(List<Category> categories) {
    	return (byte)categories.stream().map(c -> (int)Math.pow(2,c.getOrdinal())).mapToInt(i -> i).sum();
    }
    
    //Reverse bitwise converter from a bitmap to list of categories
    public static List<Category> convertToCategories(byte categoriesMap) {
    
    	List<Category> categories = new ArrayList<Category>();
    	
    	String bitMap = new StringBuilder(String.format("%5s", Integer.toBinaryString(categoriesMap & 0xFF)).replace(' ', '0')).reverse().toString();
    	for(int i=0;i<bitMap.length();i++)
    	{
    		if(Character.getNumericValue(bitMap.charAt(i)) == 1)
    			categories.add(Category.values()[i]);
    	}
    	return categories;
    }
}
