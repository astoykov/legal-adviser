package com.alesto.legaladviser.util;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alesto.legaladviser.model.LegalAdviser;
import com.alesto.legaladviser.model.OrgType;
import com.opencsv.CSVReader;

public class CSVUtils {

	public static final String	CSV_FILE = "legal-providers.csv";
	
	public static Map<Long,LegalAdviser> loadAdvisers()
	{
		Map<Long,LegalAdviser> resultMap = new HashMap<Long, LegalAdviser>();
		
		CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(CSV_FILE));
            String[] line;
            reader.readNext(); //skip the header
            while ((line = reader.readNext()) != null) {
                resultMap.put(Long.parseLong(line[0]),
                					new LegalAdviser(Long.parseLong(line[0]), //id
                								line[1], //name
                								line[2],//address
                								line[3],//city
                								line[4],//postcode
                								line[5],//phone
                								OrgType.valueOf(line[6].toUpperCase()),//orgtype
                								convertToByte(new String[] {line[7],line[8],line[9],line[10],line[11]})// category
                										));
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return resultMap;
	}
	
    private static byte convertToByte(String[] bits) {
  
    	
    	return (byte)Arrays.stream(bits).map(c -> (int)Math.pow(2,Integer.parseInt(c))).mapToInt(i -> i).sum();
    
	}
	
	    
    

}