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

import fr.eurecom.nerd.client.type.ExtractorType;
import fr.eurecom.nerd.client.type.GranularityType;

public class LookUp {
    
    

    protected static String mapExtractor(ExtractorType extractor) 
    {
        switch(extractor) 
        {
        case ALCHEMYAPI: 
            return "alchemyapi";
        case DBPEDIA_SPOTLIGHT:
            return "dbspotlight";
        case EVRI:
            return "evri";
        case EXTRACTIV:
            return "extractiv"; 
        case LUPEDIA:
            return "lupedia";           
        case OPENCALAIS:
            return "opencalais";
        case SAPLO:
            return "saplo";
        case SEMITAGS:
            return "semitags"; 
        case WIKIMETA: 
            return "wikimeta";
        case YAHOO:
            return "yahoo";
        case ZEMANTA:
            return "zemanta";
        case COMBINED:
            return "combined";
        }
        return null;
    }

    public static String mapGranularity(GranularityType granType) 
    {       
        switch(granType)
        {
        case OEN:
            return "oen";
        case OED:
            return "oed";
        }
        return null;
    }
}
