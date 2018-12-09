package com.alesto.legaladviser.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.alesto.legaladviser.model.Category;
import com.alesto.legaladviser.model.LegalAdviser;
import com.alesto.legaladviser.model.OrgType;
import com.alesto.legaladviser.util.CSVUtils;


@Repository
public class LegalAdivserDAO implements ILegalAdviserDAO {

	//The main data structure as a map indexed by id to allow a quick data access by id
	Map<Long,LegalAdviser> allAdvisers = CSVUtils.loadAdvisers();

    @Override
    public List<LegalAdviser> searchAdvisers(final List<OrgType> types, final List<Category> categories) {
    	// Some default values if params empty to allow more flexible searches with omitted params
    	final byte categoryBitMap = categories.size()>0 ? Category.convertToByte(categories) : Byte.MAX_VALUE ;
    	final List<OrgType> orgTypes = types.size()>0 ? types : Arrays.asList(OrgType.values());
    	
    	//Stream and filter using bitwise and on the category bitmap
    	return allAdvisers.values().stream()
    			.filter(r -> orgTypes.contains(r.getOrgtype()) 
    			&& (r.getCategoryMap()&categoryBitMap)>0).collect(Collectors.toList());
    }

    @Override
    public LegalAdviser getAdviser(final long id) {
        //Quick return by the index from a map. Needs exception handling and not found response
    	return allAdvisers.get(id);
    }

}
