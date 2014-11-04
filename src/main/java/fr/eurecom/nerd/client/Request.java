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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Request {

    protected enum RequestType {
      GET,
      POST
    }    
    
    protected static synchronized String request(
                                        String uri,
                                        RequestType method, 
                                        MultivaluedMap<String, String> queryParams
                                    )
    {
        Client client = Client.create();        
        WebResource webResource = client.resource(uri);
        
        String json = null;
        
        switch (method) {
        case GET:
            json = webResource.
                        queryParams(queryParams).
                        accept(MediaType.APPLICATION_JSON_TYPE).
                        get(String.class);
            break;
            
        case POST:
            json = webResource.
                        accept(MediaType.APPLICATION_JSON_TYPE). 
                        post(String.class, queryParams);
            break;

        default:
            break;
        }
        
        return json;
    }
}
