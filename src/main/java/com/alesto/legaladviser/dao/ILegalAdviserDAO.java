package com.alesto.legaladviser.dao;

import java.util.List;

import com.alesto.legaladviser.model.Category;
import com.alesto.legaladviser.model.LegalAdviser;
import com.alesto.legaladviser.model.OrgType;


public interface ILegalAdviserDAO {
    List<LegalAdviser> searchAdvisers(List<OrgType> types, List<Category> categories);

    LegalAdviser getAdviser( long id);
}
