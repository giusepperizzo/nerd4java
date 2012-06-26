//   nerd4java - A java library which provides an interface to NERD
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

import fr.eurecom.nerd.client.schema.Extraction;

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
     * @throws TypeExpection 
     *
     */
    public String extractionJSON(   ExtractorType extractor, String text,
                                    String language, Boolean duplicate
                                 ) 
    throws TypeExpection 
    {
        String extractorName = LookUp.map(extractor);
        return NERDResult.getExtractionJSON(API_HOST, 
                                            apiKey, 
                                            extractorName, 
                                            text, 
                                            language, 
                                            duplicate);
    }

    /**
    *
    */
   public List<Extraction> extraction(  ExtractorType extractor, String text,
                                        String language, Boolean duplicate
                                     )
   throws TypeExpection
   {        
       String extractorName = LookUp.map(extractor);
       return NERDResult.getExtraction(API_HOST, 
                                       apiKey, 
                                       extractorName, 
                                       text, 
                                       language, 
                                       duplicate);
   }

}
