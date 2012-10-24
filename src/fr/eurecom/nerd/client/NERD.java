//   nerd4java - A java library which provides a programmable interface to NERD
//               http://nerd.eurecom.fr
//
//   Copyright 2012
//
//   Authors:
//      Giuseppe Rizzo <giuse.rizzo@gmail.com>
//
// This program is free software; you can redistribute it and/or modify it
// under the terms of the GNU General Public License published by
// the Free Software Foundation, either version 3 of the License, or (at 
// your option) any later version. See the file Documentation/GPL3 in the
// original distribution for details. There is ABSOLUTELY NO warranty.

package fr.eurecom.nerd.client;

import java.util.List;

import fr.eurecom.nerd.client.schema.Entity;
import fr.eurecom.nerd.client.type.DocumentType;
import fr.eurecom.nerd.client.type.ExtractorType;
import fr.eurecom.nerd.client.type.GranularityType;

public class NERD {
    
    /** API URI */
    private String API_HOST = "http://nerd.eurecom.fr/api/";
       
    /** Currently used API key for API calls */
    private String apiKey;

    
    /** 
     * Main constructor that initializes all data required for API calls
     * 
     *  @param apiKey API key for server authentication. Developers can register
     *      for it at <a href="http://nerd.eurecom.fr">NERD</a>.
     */
    public NERD(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public NERD (String host, String apiKey) {
        this.API_HOST = host;
        this.apiKey = apiKey;
    }
        
    /**
     *  Get JSON 
     *
     */
    public String annotateJSON(
                                ExtractorType extType, 
                                DocumentType docuType,
                                String document
                              ) 
    {        
        return annotateJSON(extType, docuType, document, GranularityType.OEN, null);
    }
    
    public String annotateJSON(
                                ExtractorType extType, 
                                DocumentType docuType,
                                String document,
                                GranularityType granType
                               ) 
    {
        return annotateJSON(extType, docuType, document, granType, null);
    }

    public String annotateJSON(
                                ExtractorType extType, 
                                DocumentType docuType,
                                String document,
                                GranularityType granType,
                                Long timeout
                                ) 
    {
        String extractor = LookUp.mapExtractor(extType);
        String granularity = LookUp.mapGranularity(granType);
        
        return NERDResult.doAnnotationJSON( API_HOST, 
                                            apiKey, 
                                            extractor, 
                                            docuType,
                                            document,
                                            granularity,
                                            timeout);
    }


    /**
     *   Get collection of Extraction objects
     */
    public List<Entity> annotate(
                                    ExtractorType extType, 
                                    DocumentType docuType,
                                    String document
                                )
    {                
        return annotate(extType, docuType, document, GranularityType.OEN, null);
    }
    
    public List<Entity> annotate(  
                                    ExtractorType extType, 
                                    DocumentType docuType,
                                    String document,
                                    GranularityType granType
                                )
    {   
        return annotate(extType, docuType, document, granType, null);
    }

   public List<Entity> annotate(   
                                   ExtractorType extType, 
                                   DocumentType docuType,
                                   String document,
                                   GranularityType granType,
                                   Long timeout
                               ) 
   {        
       String extractor = LookUp.mapExtractor(extType);
       String granularity = LookUp.mapGranularity(granType);
       
       return NERDResult.doAnnotation( API_HOST, 
                                       apiKey, 
                                       extractor, 
                                       docuType,
                                       document,
                                       granularity, 
                                       timeout);
   }
}
