/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author andre
 */
@Document(collection = "users")
public class UsersDocument {
    
    @Id
    private String id;

    private String content;
    
    private String fileName;

    public UsersDocument(String id, String file, String fileName) {
        this.id = id;
        this.content = file;
        this.fileName = fileName;
    }

    public UsersDocument() {
    }
   
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
}