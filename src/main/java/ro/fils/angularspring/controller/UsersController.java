/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.controller;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.fils.angularspring.domain.User;
import ro.fils.angularspring.entity.UsersDocument;
import ro.fils.angularspring.repository.UsersDocumentRepository;
import ro.fils.angularspring.util.UserConverter;

/**
 *
 * @author andre
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    private UserConverter userConverter;

    @Autowired
    UsersDocumentRepository usersDocumentRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<User> getAllUsers() {
        userConverter = new UserConverter();
        return userConverter.readAllUsers(usersDocumentRepository.findOne("564f441aecece47bba5ff132").getContent());

    }

    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    User insertUser(@RequestBody User user) {
        userConverter = new UserConverter();
        user.setId(UUID.randomUUID().toString());
        String content = usersDocumentRepository.findOne("564f441aecece47bba5ff132").getContent();
        usersDocumentRepository.save(new UsersDocument("564f441aecece47bba5ff132",userConverter.insertUser(user, content),"users"));
        return user;
    }
    
//    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
//    public
//    @ResponseBody
//    User getOneUser(@PathVariable("userId") String userId) {
//        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
//        return departmentService.getDepartment(departmentId);
//    }
    
}
