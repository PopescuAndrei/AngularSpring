/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.fils.angularspring.repository.ProjectsDocumentRepository;
import ro.fils.angularspring.util.ProjectConverter;
import ro.fils.semanticapp.model.Project;

/**
 *
 * @author andre
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private ProjectConverter projectConverter;

    @Autowired
    ProjectsDocumentRepository projectsDocumentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{projectId}")
    public @ResponseBody
    Project getOneProject(@PathVariable("projectId") String projectId) {
        projectConverter = new ProjectConverter();
        ArrayList<Project> projects = projectConverter.readAll(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());
        Project project = null;
        for (Project p : projects) {
            if (projectId.equals(p.getId())) {
                project = p;
            }
        }
        return project;
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getAllProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.readAll(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getAllCompleteProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllCompleteProjects(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }
}
