/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ro.fils.angularspring.domain.User;

/**
 *
 * @author andre
 */
public class UserConverter {
    
    public ArrayList<User> readAllUsers(String xml) {
        ArrayList<User> users = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                User user = null;
                if (node instanceof Element) {
                    user = new User();
                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node cNode = childNodes.item(j);
                        if (cNode instanceof Element) {
                            String content = cNode.getLastChild().getTextContent().trim();
                            switch (cNode.getNodeName()) {
                                case "id": {
                                    user.setId(content);
                                    break;
                                }
                                case "username": {
                                    user.setUsername(content);
                                    break;
                                }
                                case "role": {
                                    user.setRole(content);
                                    break;
                                }
                                case "mail": {
                                    user.setMail(content);
                                    break;
                                }
                                case "password": {
                                    user.setPassword(content);
                                    break;
                                }
                                case "first-name": {
                                    user.setFirstName(content);
                                    break;
                                }
                                case "last-name": {
                                    user.setLastName(content);
                                    break;
                                }
                            }
                        }
                    }
                    users.add(user);
                }

            }
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(UserConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (User user : users) {
            System.out.println(user.getFirstName());
        }
        return users;
    }

    public String insertUser(User user, String xml) {
        ArrayList<User> users = readAllUsers(xml);
        boolean found = false;
        for (User u : users) {
            if (u.getMail().equals(user.getMail())) {
                found = true;
            }
        }
        String s = null;
        if (found == false) {
            users.add(user);
            s = writeUserInXml(users);
        }
        return s;
    }

    public String writeUserInXml(ArrayList<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<users>");
        for (User u : users) {
            stringBuilder.append(u.toString());
        }
        stringBuilder.append("</users>");
        return stringBuilder.toString();
    }
}