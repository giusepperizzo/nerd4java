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


package fr.eurecom.nerd.client.schema;

import java.util.Comparator;

public class Extraction {
    private Integer idExtraction;
    private String entity;
    private Integer startChar;
    private Integer endChar;
    private String type;
    private String nerdType;
    private String uri;
    private Double confidence;
    private Double relevance;
    private String source;
    private Double startNPT;
    private Double endNPT;
    
    public Extraction(int idExtraction, String entity, String type,
            String uri, String nerdType, Integer startChar,
            Integer endChar, double confidence, double relevance,
            String source, Double startNpt, Double endNpt) 
    {
        this(entity,type,uri,nerdType,startChar,endChar,confidence,relevance,
                source,startNpt,endNpt);
        this.setIdExtraction(idExtraction);
    }

    
    public Extraction ( String entity, 
                        String type, 
                        String uri, 
                        String nerdtype, 
                        Double confidence,
                        String source)
    {
        this.setEntity(entity);
        this.setType(type);
        this.setUri(uri);
        this.setNerdType(nerdtype);
        this.setConfidence(confidence);
        this.setSource(source);
    }

    public Extraction( 
                       String entity, String type, String uri, String nerdType, 
                       Integer startchar, Integer endchar, Double confidence, String source
                      ) 
    {
        this(entity,type,uri,nerdType,confidence, source); 
        this.setStartChar(startchar);
        this.setEndChar(endchar);
    }


    public Extraction(  String entity, String type, String uri, String nerdType, 
                        Integer startChar, Integer endChar, Double confidence,
                        Double relevance, String source,
                        Double startNPT, Double endNPT) 
    {
        this(entity,type,uri,nerdType, confidence, source); 
        this.setStartChar(startChar);
        this.setEndChar(endChar);
        this.setStartNPT(startNPT);
        this.setEndNPT(endNPT);
        this.setRelevance(relevance);
    }
    
    public Integer getStartChar() {
        return startChar;
    }
    public void setStartChar(Integer startChar) {
        this.startChar = startChar;
    }
    public Integer getIdExtraction() {
        return idExtraction;
    }
    public void setIdExtraction(Integer idExtraction) {
        this.idExtraction = idExtraction;
    }
    public String getEntity() {
        return entity;
    }
    public void setEntity(String entity) {
        this.entity = entity;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getEndChar() {
        return endChar;
    }
    public void setEndChar(Integer endChar) {
        this.endChar = endChar;
    }
    public String getNerdType() {
        return nerdType;
    }
    public void setNerdType(String nerdType) {
        this.nerdType = nerdType;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public Double getConfidence() {
        return confidence;
    }
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
    public Double getRelevance() {
        return relevance;
    }
    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }
    
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public Double getStartNPT() {
        return startNPT;
    }

    public void setStartNPT(Double startNPT) {
        this.startNPT = startNPT;
    }

    public Double getEndNPT() {
        return endNPT;
    }

    public void setEndNPT(Double endNPT) {
        this.endNPT = endNPT;
    }

    public static final Comparator<Extraction> ENTITYPOSITION = 
            new Comparator<Extraction>() {
                public int compare(Extraction e1, Extraction e2) {  
                    int position = e1.getStartChar().compareTo(e2.getStartChar());
                    if(position == 0) 
                        position = e2.getEndChar().compareTo(e1.getEndChar());
                    
                    return position;
                }
            };
}
