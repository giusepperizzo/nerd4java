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

public class Entity {
    private Integer idEntity;
    private String label;
    private Integer startChar;
    private Integer endChar;
    private String extractorType;
    private String nerdType;
    private String uri;
    private Double confidence;
    private Double relevance;
    private String extractor;
    private Double startNPT;
    private Double endNPT;
    
    public Entity(int idEntity, String label, String type,
            String uri, String nerdType, Integer startChar,
            Integer endChar, double confidence, double relevance,
            String source, Double startNpt, Double endNpt) 
    {
        this(label,type,uri,nerdType,startChar,endChar,confidence,relevance,
                source,startNpt,endNpt);
        this.setIdEntity(idEntity);
    }

    
    public Entity (     
                    String label, 
                    String type, 
                    String uri, 
                    String nerdtype, 
                    Double confidence,
                    String source
                  )
    {
        this.setLabel(label);
        this.setExtractorType(type);
        this.setUri(uri);
        this.setNerdType(nerdtype);
        this.setConfidence(confidence);
        this.setExtractor(source);
    }

    public Entity( 
                   String label, String type, String uri, String nerdType, 
                   Integer startchar, Integer endchar, Double confidence, String source
                  ) 
    {
        this(label,type,uri,nerdType,confidence, source); 
        this.setStartChar(startchar);
        this.setEndChar(endchar);
    }


    public Entity(
                    String label, String type, String uri, String nerdType, 
                    Integer startChar, Integer endChar, Double confidence,
                    Double relevance, String source,
                    Double startNPT, Double endNPT
                 ) 
    {
        this(label, type,  uri, nerdType, confidence, source); 
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
    public Integer getIdEntity() {
        return idEntity;
    }
    public void setIdEntity(Integer idEntity) {
        this.idEntity = idEntity;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getExtractorType() {
        return extractorType;
    }
    public void setExtractorType(String extractorType) {
        this.extractorType = extractorType;
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
    
    public String getExtractor() {
        return extractor;
    }
    public void setExtractor(String extractor) {
        this.extractor = extractor;
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

    public static final Comparator<Entity> ENTITYPOSITION = 
            new Comparator<Entity>() {
                public int compare(Entity e1, Entity e2) {  
                    int position = e1.getStartChar().compareTo(e2.getStartChar());
                    if(position == 0) 
                        position = e2.getEndChar().compareTo(e1.getEndChar());
                    
                    return position;
                }
            };
}
