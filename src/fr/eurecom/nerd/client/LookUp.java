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

public class LookUp {

    protected static String map(Extractor extractor) throws TypeExpection 
    {
        switch(extractor) 
        {
        case ALCHEMYAPI: 
            return "alchemyapi";
        case DBPEDIA_SPOTLIGHT:
            return "spotlight";
        case EVRI:
            return "evri";
        case EXTRACTIV:
            return "extractiv"; 
        case ONTOTEXT:
            return "ontotext";
        case OPENCALAIS:
            return "opencalais";  
        case SAPLO:
            return "saplo";
        case WIKIMETA: 
            return "wikimeta";
        case YAHOO:
            return "yahoo";
        case ZEMANTA:
            return "zemanta";
        default:
            throw new TypeExpection(extractor + " is not supported by the NERD platform yet. " +
            		"If you are interested to use this extractor through NERD, please send an " +
            		"email to giuseppe.rizzo@eurecom.fr\n");
        }
        
    }
}
