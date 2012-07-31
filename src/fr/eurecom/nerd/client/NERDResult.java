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

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import fr.eurecom.nerd.client.schema.Annotation;
import fr.eurecom.nerd.client.schema.Document;
import fr.eurecom.nerd.client.schema.Extraction;
import fr.eurecom.nerd.client.type.DocumentType;

public class NERDResult extends Request{
    
    protected static String getExtractionJSON(
                                                String uri,
                                                String apiKey,
                                                String extractor,
                                                DocumentType docuType,
                                                String language,
                                                String text, 
                                                Long timeout,
                                                boolean duplicate
                                              ) 
    {
        Gson gson = new Gson();
        MultivaluedMap<String,String> params =  new MultivaluedMapImpl();
        
        params.add("key", apiKey);        
        switch (docuType) {
        case PLAINTEXT: params.add("text", text);       break;
        case TIMEDTEXT: params.add("timedtext", text);  break;
        }        
        String jsonDocument = 
                    request(uri.concat("document"), RequestType.POST, params);
        Document document = gson.fromJson(jsonDocument, Document.class);
        
        // create annotation
        params.clear();
        params.add("key", apiKey);
        params.add("extractor", extractor);
        params.add("idDocument", document.getIdDocument());
        params.add("language", language);
        if(timeout!=null) params.add("timeout", timeout.toString());
        String jsonAnnotation = request(uri.concat("annotation"), RequestType.POST, params);
        Annotation annotation = gson.fromJson(jsonAnnotation, Annotation.class);
        
        // read extraction from annotation
        params.clear();
        params.add("key", apiKey);
        params.add("idAnnotation", annotation.getIdAnnotation().toString());
        params.add("duplicate", (duplicate)?"true":"false");
        String jsonExtraction = request(uri.concat("extraction"), RequestType.GET, params);
        
        return jsonExtraction;
    }
    
    protected static List<Extraction> getExtraction(
                                                    String uri,
                                                    String apiKey,
                                                    String extractor,
                                                    DocumentType docuType,
                                                    String language,
                                                    String text, 
                                                    Long timeout,
                                                    boolean duplicate
                                                   ) 
    {
        Gson gson = new Gson();
        MultivaluedMap<String,String> params =  new MultivaluedMapImpl();
        
        params.add("key", apiKey);
        switch (docuType) {
        case PLAINTEXT: params.add("text", text);       break;
        case TIMEDTEXT: params.add("timedtext", text);  break;
        }
        String jsonDocument = 
                request(uri.concat("document"), RequestType.POST, params);
        Document document = gson.fromJson(jsonDocument, Document.class);
        
        // create annotation
        params.clear();
        params.add("key", apiKey);
        params.add("extractor", extractor);
        params.add("idDocument", document.getIdDocument());
        params.add("language", language);
        if(timeout!=null) params.add("timeout", timeout.toString());
        String jsonAnnotation = request(uri.concat("annotation"), RequestType.POST, params);
        Annotation annotation = gson.fromJson(jsonAnnotation, Annotation.class);
        
        // read extraction from annotation
        params.clear();        
        params.add("key", apiKey);
        params.add("idAnnotation", annotation.getIdAnnotation().toString());
        params.add("duplicate", (duplicate)?"true":"false");
        String jsonExtraction = request(uri.concat("extraction"), RequestType.GET, params);
        
        Type listType = new TypeToken<List<Extraction>>(){}.getType();
        List<Extraction> extractions = gson.fromJson(jsonExtraction, listType);
                
        return extractions;
    }
}
