package com.alesto.legaladviser;

import static org.junit.Assert.*;

import com.alesto.legaladviser.model.Category;

import static com.alesto.legaladviser.model.Category.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CategoryTest {

	@Test
	public void testToByte() {
		List<Category> categories = new ArrayList<Category>(Arrays.asList(Category.values()));
		
		assertEquals(31, Category.convertToByte(categories));
		
		
	}
	
	@Test
	public void testToByte2() {
		List<Category> categories = new ArrayList<Category>(Arrays.asList(new Category[] {DEBT,HOUSING,IMMIGRATION,WELLFARE}));
		
		assertEquals(30, Category.convertToByte(categories));
		
		
	}
	

	@Test
	public void testToByteEmpty() {
		List<Category> categories = new ArrayList<Category>();
		
		assertEquals(0, Category.convertToByte(categories));
		
		
	}
	
	@Test
	public void testFromByte() {
		List<Category> categories = Category.convertToCategories((byte)31);
		
		assertEquals(5, categories.size());
		
	}
	
	
	@Test
	public void testToFromByte() {
		List<Category> categories = Category.convertToCategories((byte)7);
				
		assertEquals(7,Category.convertToByte(categories));
	}
	

}
