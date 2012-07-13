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

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import fr.eurecom.nerd.client.schema.Document;
import fr.eurecom.nerd.client.schema.Extraction;

public class NERDResult extends Request{
    
    protected static String getExtractionJSON(
                                    String uri,
                                    String apiKey,
                                    String extractor,
                                    String text, 
                                    String type,
                                    String language,
                                    boolean duplicate
                                    ) 
    {
        Gson gson = new Gson();
        MultivaluedMap<String,String> params =  new MultivaluedMapImpl();
        
        params.add("key", apiKey);
        params.add("text", text);
        params.add("type", type);
        String jsonDocument = 
                    request(uri.concat("document"), RequestType.POST, params);
        Document document = gson.fromJson(jsonDocument, Document.class);
        
        // create annotation
        params.remove("text");
        params.add("idDocument", document.getIdDocument());
        params.add("language", language);
        String jsonExtraction =
                    request(uri.concat("annotation/").concat(extractor), 
                            RequestType.POST, params);
        Extraction extraction = gson.fromJson(jsonExtraction, Extraction.class);
        
        // read extraction from annotation
        params.remove("idDocument");
        params.remove("language");
        params.add("duplicate", (duplicate)?"true":"false");
        jsonExtraction = 
                request(uri.concat("extraction/").concat(extraction.getIdExtraction().toString()), 
                        RequestType.GET, params);
        
        return jsonExtraction;
    }
    
    protected static List<Extraction> getExtraction(
                                        String uri,
                                        String apiKey,
                                        String extractor,
                                        String text, 
                                        String type,
                                        String language,
                                        boolean duplicate
                                        ) 
    {
        Gson gson = new Gson();
        MultivaluedMap<String,String> params =  new MultivaluedMapImpl();
        
        params.add("key", apiKey);
        params.add("text", text);
        params.add("type", type);
        String jsonDocument = 
                request(uri.concat("document"), RequestType.POST, params);
        Document document = gson.fromJson(jsonDocument, Document.class);
        
        // create annotation
        params.remove("text");
        params.add("idDocument", document.getIdDocument());
        params.add("language", language);
        String jsonExtraction =
                request(uri.concat("annotation/").concat(extractor), 
                    RequestType.POST, params);
        Extraction extraction = gson.fromJson(jsonExtraction, Extraction.class);
        
        // read extraction from annotation
        params.remove("idDocument");
        params.remove("language");
        params.add("idExtraction", extraction.getIdExtraction().toString());
        params.add("duplicate", (duplicate)?"true":"false");
        jsonExtraction = 
                request(uri.concat("extraction/").concat(extraction.getIdExtraction().toString()), 
                        RequestType.GET, params);
        
        Type listType = new TypeToken<List<Extraction>>(){}.getType();
        List<Extraction> extractions = gson.fromJson(jsonExtraction, listType);
                
        return extractions;
    }
}
