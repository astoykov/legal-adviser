package com.alesto.legaladviser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LegalAdviserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void legalAdviserById() throws Exception {
        
        this.mockMvc.perform(get("/advisers/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void legalAdvisersSearchOrgType() throws Exception {
        
        this.mockMvc.perform(get("/advisers?type=charity")).andDo(print())
        		.andExpect(status().isOk())
                .andExpect(jsonPath("$[:1].orgtype").value("CHARITY"))
                .andExpect(jsonPath("$[?(@.orgtype=='MEDIATION')]").doesNotExist());
    }
    
    @Test
    public void legalAdvisersSearchOrgTypeCategory() throws Exception {
        
        this.mockMvc.perform(get("/advisers?type=charity&category=crime&type=mediation&category=debt")).andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$[?(@.orgtype=='CHARITY')]").exists())
                .andExpect(jsonPath("$[?(@.orgtype=='PRIVATE')]").doesNotExist())
                .andExpect(jsonPath("$[0].categories[0]").value("DEBT"));
    }
   

}
