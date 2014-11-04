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

public class Annotation {
    private Integer idAnnotation;
    private Integer articleIdArticle;
    private Integer toolIdTool;
    private Integer userIdUser;
    
    public Integer getArticleIdArticle() {
        return articleIdArticle;
    }
    public void setArticleIdArticle(Integer articleIdArticle) {
        this.articleIdArticle = articleIdArticle;
    }
    public Integer getIdAnnotation() {
        return idAnnotation;
    }
    public void setIdAnnotation(Integer idAnnotation) {
        this.idAnnotation = idAnnotation;
    }
    public Integer getToolIdTool() {
        return toolIdTool;
    }
    public void setToolIdTool(Integer toolIdTool) {
        this.toolIdTool = toolIdTool;
    }
    public Integer getUserIdUser() {
        return userIdUser;
    }
    public void setUserIdUser(Integer userIdUser) {
        this.userIdUser = userIdUser;
    }
}
