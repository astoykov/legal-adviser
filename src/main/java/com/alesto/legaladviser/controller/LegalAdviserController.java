package com.alesto.legaladviser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alesto.legaladviser.dao.ILegalAdviserDAO;
import com.alesto.legaladviser.model.Category;
import com.alesto.legaladviser.model.LegalAdviser;
import com.alesto.legaladviser.model.OrgType;

@RestController
public class LegalAdviserController {

	//Sample query:
	//http://localhost:8080/advisers?type=charity&type=private&category=crime&type=mediation&category=debt&category=housing
	
	 @Autowired
	 private ILegalAdviserDAO service; 
	
    @RequestMapping(value = "/advisers/{id}")
    @ResponseBody
    public LegalAdviser getFoosBySimplePathWithPathVariable(@PathVariable final long id) {
        return service.getAdviser(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/advisers")
    @ResponseBody
    public List<LegalAdviser> findAll(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "category", required = false) String category) {

    	List<OrgType> types = new ArrayList<OrgType>();
    	List<Category> categories = new ArrayList<Category>();
    	
    	//Parsing
    	if(type!=null){
    		for (String t : type.split(",")) {
    	
			try{
				types.add(OrgType.valueOf(t.toUpperCase()));
			}
			catch(IllegalArgumentException ie)
			{}
    		}
		}
    	
    	if(category!=null){
	    	for (String c : category.split(",")) {
				try{
					categories.add(Category.valueOf(c.toUpperCase()));
				}
				catch(IllegalArgumentException ie)
				{}
			}
    	}
    	
    	return service.searchAdvisers(types,categories);
    }
}
