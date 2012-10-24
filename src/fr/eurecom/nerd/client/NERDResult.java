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
import fr.eurecom.nerd.client.schema.Entity;
import fr.eurecom.nerd.client.type.DocumentType;
import fr.eurecom.nerd.client.type.ExtractorType;

public class NERDResult extends Request{
        
    /*
     *  post object and get its id
     */
    protected static Integer postDocument  (   String uri, 
                                               String apiKey,
                                               DocumentType docuType,
                                               String text ) 
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
        return document.getIdDocument();
    }
    
    protected static Integer postAnnotation ( String uri,
                                              String apiKey,
                                              ExtractorType extType, 
                                              Long timeout,
                                              Integer idDocument )
    {
        Gson gson = new Gson();
        MultivaluedMap<String,String> params =  new MultivaluedMapImpl();
        String extractor = LookUp.mapExtractor(extType);
        
        params.add("key", apiKey);
        params.add("extractor", extractor);
        params.add("idDocument", idDocument.toString());
        if(timeout!=null) params.add("timeout", timeout.toString());
        String jsonAnnotation = request(uri.concat("annotation"), RequestType.POST, params);
        Annotation annotation = gson.fromJson(jsonAnnotation, Annotation.class);
        
        return annotation.getIdAnnotation();
    }
    
    protected static String getExtractions( String uri,
                                            String apiKey,
                                            Integer idAnnotation,
                                            String granularity)
    {
        MultivaluedMap<String,String> params =  new MultivaluedMapImpl();
        
        // read extraction from annotation
        params.add("key", apiKey);
        params.add("idAnnotation", idAnnotation.toString());
        params.add("granularity", granularity);
        String jsonExtraction = request(uri.concat("entity"), RequestType.GET, params);
        
        return jsonExtraction;
    }
    
    
    /*
     *  Abstraction methods to get the extraction results:
     *  - JSON
     *  - List<Extraction>
     */
    protected static String doAnnotationJSON(
                                                String uri,
                                                String apiKey,
                                                String extractor,
                                                DocumentType docuType,
                                                String text, 
                                                String granularity,
                                                Long timeout
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
        params.add("idDocument", document.getIdDocument().toString());
        if(timeout!=null) params.add("timeout", timeout.toString());
        String jsonAnnotation = request(uri.concat("annotation"), RequestType.POST, params);
        Annotation annotation = gson.fromJson(jsonAnnotation, Annotation.class);
        
        // read extraction from annotation
        params.clear();
        params.add("key", apiKey);
        params.add("idAnnotation", annotation.getIdAnnotation().toString());
        if(granularity!=null) params.add("granularity", granularity );
        String json = request(uri.concat("entity"), RequestType.GET, params);
        
        return json;
    }
    
    protected static List<Entity> doAnnotation(
                                                    String uri,
                                                    String apiKey,
                                                    String extractor,
                                                    DocumentType docuType,
                                                    String text, 
                                                    String granularity,
                                                    Long timeout
                                                ) 
    {
        Gson gson = new Gson();
        
        String json = doAnnotationJSON(uri, apiKey, extractor, docuType, text, granularity, timeout);
        
        Type listType = new TypeToken<List<Entity>>(){}.getType();
        List<Entity> entities = gson.fromJson(json, listType);
                
        return entities;
    }
}
