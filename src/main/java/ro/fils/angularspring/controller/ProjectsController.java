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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.fils.angularspring.domain.Project;
import ro.fils.angularspring.entity.ProjectsDocument;
import ro.fils.angularspring.repository.ProjectsDocumentRepository;
import ro.fils.angularspring.util.ProjectConverter;
/**
 *
 * @author andre
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {
/*
    CONNECTION STRINGS
    
    */
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

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Project insertProject(@RequestBody Project project) {
        projectConverter = new ProjectConverter();
        project.setId(UUID.randomUUID().toString());
        String content = projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent();
        projectsDocumentRepository.save(new ProjectsDocument("564f596becece47bba5ff133",projectConverter.insertProject(project, content),"projects"));
        return project;
    }
    
    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getAllCompleteProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllCompleteProjects(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }

    @RequestMapping(value = "/getWithBudget", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getWithBudgetProjects(@RequestParam("budget") String budget, @RequestParam("operator") String operator) {
        projectConverter = new ProjectConverter();
        if (operator.equals("Greater")) {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent(), "budget", budget, ">");
        } else {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent(), "budget", budget, "<");

        }
    }

    @RequestMapping(value = "/getWithDuration", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getWithDuration(@RequestParam("duration") String duration, @RequestParam("operator") String operator) {
        projectConverter = new ProjectConverter();
        if (operator.equals("Greater")) {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent(), "duration", duration, ">");
        } else {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent(), "duration", duration, "<");

        }
    }

    @RequestMapping(value = "/getWithBudgetDuration", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getWithBudgetDuration() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByBudgetDuration(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }

    @RequestMapping(value = "/getProjectsMorePartners", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getProjectsMorePartners() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByPartnerNo(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }

    @RequestMapping(value = "/getProjectsWorkingPackages", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getProjectsWorkingPackages() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByStagesNo(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }

    @RequestMapping(value = "/getSumProjects", method = RequestMethod.GET)
    public @ResponseBody
    Double getSumProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getBudgetSum(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }

    @RequestMapping(value = "/getNoPmProjects", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getNoPMProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsWithNoPM(projectsDocumentRepository.findOne("564f596becece47bba5ff133").getContent());

    }
}
