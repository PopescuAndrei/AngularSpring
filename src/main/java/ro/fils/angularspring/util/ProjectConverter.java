/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.util;

/**
 *
 * @author andre
 */
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ro.fils.angularspring.domain.Partner;
import ro.fils.angularspring.domain.Project;
import ro.fils.angularspring.domain.Stage;

/**
 *
 * @author Vlad
 */
public class ProjectConverter {

    public ArrayList<Project> readAll(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            NodeList nodeList = document.getElementsByTagName("project");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Project project = parseProject(node);
                projects.add(project);

            }
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Project project : projects) {
            System.out.println(project.getTitle());
        }
        return projects;
    }

    public ArrayList<Project> getAllProjectsByTagValue(String xml, String tag, String value, String operator) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[" + tag + " " + operator + " '" + value + "'] ");
            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
                projects.add(p);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public ArrayList<Project> getAllProjectsByBudgetDuration(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[budget > 20000 and duration > 80] ");
            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
                projects.add(p);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public ArrayList<Project> getAllProjectsByPartnerNo(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[count(partners/partner)>2]");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
                projects.add(p);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public ArrayList<Project> getAllProjectsByStagesNo(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[count(stages/stage)>2 and count(stages/stage)<6]");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
                projects.add(p);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public ArrayList<Project> getAllProjectsByStagesNoZero(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[count(stages/stage)=0]");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public ArrayList<Project> getAllCompleteProjects(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[/* != '']");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
                projects.add(p);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public double getBudgetSum(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        double sum = 0;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("sum(/projects/project/budget)");

            Double result = (Double) expr.evaluate(document, XPathConstants.NUMBER);
            sum = result;
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sum;
    }

    public ArrayList<Project> getAllProjectsWithNoPM(String xml) {
        ArrayList<Project> projects = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("/projects/project[stages/stage/name/text() != 'Project Management']");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Project p = parseProject(nodes.item(i));
                projects.add(p);
            }
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    public Double getExcessBudgetSum(String xml) {
        int totalBudget = 150000000;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        Double result = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile("sum(/projects/project/budget)");
            result = totalBudget - (Double) expr.evaluate(document, XPathConstants.NUMBER);
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(ProjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Project parseProject(Node node) {
        Project project = null;
        if (node instanceof Element) {
            project = new Project();
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node cNode = childNodes.item(j);
                if (cNode instanceof Element) {

                    if (cNode.getLastChild() != null) {
                        String content = cNode.getLastChild().getTextContent().trim();
                        switch (cNode.getNodeName()) {
                            case "id": {
                                project.setId(content);
                                break;
                            }
                            case "title": {
                                project.setTitle(content);
                                break;
                            }
                            case "budget": {
                                project.setBudget(Integer.parseInt(content));
                                break;
                            }
                            case "duration": {
                                project.setDuration(Integer.parseInt(content));
                                break;
                            }
                            case "partners": {
                                ArrayList<Partner> partners = new ArrayList<>();
                                NodeList partnerNodes = ((Element) cNode).getElementsByTagName("partner");

                                for (int k = 0; k < partnerNodes.getLength(); k++) {
                                    Node partnerNode = partnerNodes.item(k);
                                    Partner partner = null;
                                    if (partnerNode instanceof Element) {
                                        partner = new Partner();
                                        //NodeList partnerChildNodes = partnerNode.getChildNodes();

                                        partner.setName(partnerNode.getChildNodes().item(0).getTextContent().trim());
                                        partner.setLeader(Boolean.parseBoolean(partnerNode.getChildNodes().item(1).getTextContent().trim()));
                                    }
                                    partners.add(partner);
                                }
                                project.setPartners(partners);
                                break;
                            }
                            case "domain": {
                                project.setDomain(content);
                                break;
                            }
                            case "objective": {
                                project.setObjective(content);
                                break;
                            }

                            case "stages": {
                                ArrayList<Stage> stages = new ArrayList<>();
                                NodeList stageNodes = cNode.getChildNodes();
                                for (int t = 0; t < stageNodes.getLength(); t++) {
                                    Node stageNode = stageNodes.item(t);
                                    Stage stage = null;
                                    if (stageNode instanceof Element) {
                                        stage = new Stage();
                                        NodeList stageChildNodes = stageNode.getChildNodes();
                                        for (int s = 0; s < stageChildNodes.getLength(); s++) {
                                            Node sPartnerNode = stageChildNodes.item(s);
                                            if (sPartnerNode instanceof Element) {
                                                String contentStage = sPartnerNode.getLastChild().getTextContent().trim();
                                                switch (sPartnerNode.getNodeName()) {
                                                    case "name": {
                                                        stage.setName(contentStage);
                                                        break;
                                                    }
                                                    case "duration": {
                                                        stage.setDuration(Integer.parseInt(contentStage));
                                                        break;
                                                    }
                                                    case "description": {
                                                        stage.setDescription(contentStage);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    stages.add(stage);
                                }
                                project.setStages(stages);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return project;
    }

    public String insertProject(Project project, String xml) {
        ArrayList<Project> projects = readAll(xml);
        String s = null;
        projects.add(project);
        s = writeProjectInXml(projects);
        return s;
    }

    public String writeProjectInXml(ArrayList<Project> projects) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<projects>");
        for (Project p : projects) {
            stringBuilder.append(p.toString());
        }
        stringBuilder.append("</projects>");
        return stringBuilder.toString();
    }
}
