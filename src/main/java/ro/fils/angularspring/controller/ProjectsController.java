/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.controller;

import com.itextpdf.text.BadElementException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import ro.fils.angularspring.util.ConnectionUtils;
import ro.fils.angularspring.util.PDFCreator;
import ro.fils.angularspring.util.ProjectConverter;
import ro.fils.angularspring.util.XSLTFile;

/**
 *
 * @author andre
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private static final int BUFFER_SIZE = 4096;

    private ProjectConverter projectConverter;

    @Autowired
    ServletContext servletContext;

    @Autowired
    ProjectsDocumentRepository projectsDocumentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{projectId}")
    public @ResponseBody
    Project getOneProject(@PathVariable("projectId") String projectId) {
        projectConverter = new ProjectConverter();
        ArrayList<Project> projects = projectConverter.readAll(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());
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
        return projectConverter.readAll(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Project insertProject(@RequestBody Project project) {
        projectConverter = new ProjectConverter();
        project.setId(UUID.randomUUID().toString());
        String content = projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent();
        projectsDocumentRepository.save(new ProjectsDocument(ConnectionUtils.PROJECTS_COLLECTION, projectConverter.insertProject(project, content), "projects"));
        return project;
    }

    @RequestMapping(value = "/domain", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getAllProjects(@RequestParam("domain") String domain) {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent(),"domain",domain,"=");

    }
    
    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getAllCompleteProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllCompleteProjects(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(value = "/getWithBudget", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getWithBudgetProjects(@RequestParam("budget") String budget, @RequestParam("operator") String operator) {
        projectConverter = new ProjectConverter();
        if (operator.equals("Greater")) {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent(), "budget", budget, ">");
        } else {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent(), "budget", budget, "<");

        }
    }

    @RequestMapping(value = "/getWithDuration", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getWithDuration(@RequestParam("duration") String duration, @RequestParam("operator") String operator) {
        projectConverter = new ProjectConverter();
        if (operator.equals("Greater")) {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent(), "duration", duration, ">");
        } else {
            return projectConverter.getAllProjectsByTagValue(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent(), "duration", duration, "<");

        }
    }

    @RequestMapping(value = "/getWithBudgetDuration", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getWithBudgetDuration() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByBudgetDuration(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(value = "/getProjectsMorePartners", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getProjectsMorePartners() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByPartnerNo(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(value = "/getProjectsWorkingPackages", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getProjectsWorkingPackages() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsByStagesNo(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(value = "/getSumProjects", method = RequestMethod.GET)
    public @ResponseBody
    Double getSumProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getBudgetSum(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(value = "/getExcess", method = RequestMethod.GET)
    public @ResponseBody
    Double getExcess() {
        projectConverter = new ProjectConverter();
        return projectConverter.getExcessBudgetSum(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }
    
    @RequestMapping(value = "/getNoPmProjects", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Project> getNoPMProjects() {
        projectConverter = new ProjectConverter();
        return projectConverter.getAllProjectsWithNoPM(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/downloadPDF")
    public void downloadPDF(HttpServletRequest request,
            HttpServletResponse response) throws FileNotFoundException, IOException, BadElementException {
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        String fullPath = appPath + "Projects.pdf";
        PDFCreator creator = new PDFCreator();
        projectConverter = new ProjectConverter();
        creator.saveAsPDF(projectConverter.readAll(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent()), fullPath);
        System.out.println("appPath = " + appPath);

        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/downloadXML")
    public void downloadXML(HttpServletRequest request,
            HttpServletResponse response) throws FileNotFoundException, IOException, BadElementException {
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        String fullPath = appPath + "Projects.xml";
        PDFCreator creator = new PDFCreator();
        creator.saveAsXML(projectsDocumentRepository.findOne(ConnectionUtils.PROJECTS_COLLECTION).getContent(), fullPath);
        System.out.println("appPath = " + appPath);

        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/downloadXSLT")
    public void downloadXSLT(HttpServletRequest request,
            HttpServletResponse response) throws FileNotFoundException, IOException, BadElementException {
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        String fullPath = appPath + "Projects.xsl";
        PDFCreator creator = new PDFCreator();
        creator.saveAsXSLT(XSLTFile.xsltString, fullPath);
        System.out.println("appPath = " + appPath);

        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }
}
