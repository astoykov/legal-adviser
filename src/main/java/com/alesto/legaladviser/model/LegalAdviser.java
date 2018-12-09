package com.alesto.legaladviser.model;

import java.util.List;

public class LegalAdviser {

    private final long id;
    private final String name;
    private final String address;
    private final String city;
    private final String postcode;
    private final String phone;
    private final OrgType orgtype;
    /* Binary map */
    private final byte categoryMap;
    
    
	public LegalAdviser(long id, String name, String address, String city,
			String postcode, String phone, OrgType orgtype, byte categoryMap) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.phone = phone;
		this.orgtype = orgtype;
		this.categoryMap = categoryMap;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getPhone() {
		return phone;
	}
	public OrgType getOrgtype() {
		return orgtype;
	}
	public byte getCategoryMap() {
		return categoryMap;
	}
	public List<Category> getCategories() {
		return Category.convertToCategories(categoryMap);
	}

    
}
