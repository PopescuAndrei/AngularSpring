/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fils.angularspring.entity.UsersDocument;

/**
 *
 * @author andre
 */
public interface UsersDocumentRepository extends MongoRepository<UsersDocument, String>{
    
}
