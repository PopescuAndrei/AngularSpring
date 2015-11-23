/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ro.fils.angularspring.domain.Partner;
import ro.fils.angularspring.domain.Project;
import ro.fils.angularspring.domain.Stage;

/**
 *
 * @author andre
 */
public class PDFCreator {

    public void saveAsPDF(ArrayList<Project> projects, String path) throws BadElementException, IOException {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            for (Project project : projects) {
                document.add(new Paragraph("Title:" + project.getTitle() + "\nBudget: " + project.getBudget() + "\nDuration: " + project.getDuration() + "\nDomain: " + project.getDomain() + "\nObjective: " + project.getObjective()));
                document.add(new Paragraph("\nPARTNERS LIST: "));
                for (Partner p : project.getPartners()) {
                    System.out.println(p.getName());
                    if (p.isLeader()) {
                        document.add(new Paragraph("\r\nLeader: " + p.getName()));

                    } else {
                        document.add(new Paragraph("\r\nPartner: " + p.getName()));
                    }
                }
                document.add(new Paragraph("\nSTAGES: "));
                if (project.getStages() != null) {
                    for (Stage stage : project.getStages()) {
                        document.add(new Paragraph("\r\nStage: " + stage.getName() + ", Description: " + stage.getDescription() + ", Duration: " + stage.getDuration()));
                    }
                }

                document.add(new Paragraph("\n"));
                document.add(new Paragraph("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-"));
                document.add(new Paragraph("\n"));
            }

            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveProjectAsPDF(Project project, String path) throws BadElementException, IOException {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(new Paragraph("Title:" + project.getTitle() + "\nBudget: " + project.getBudget() + "\nDuration: " + project.getDuration() + "\nDomain: " + project.getDomain() + "\nObjective: " + project.getObjective()));
            document.add(new Paragraph("\nPARTNERS LIST: "));
            for (Partner p : project.getPartners()) {
                System.out.println(p.getName());
                if (p.isLeader()) {
                    document.add(new Paragraph("\r\nLeader: " + p.getName()));

                } else {
                    document.add(new Paragraph("\r\nPartner: " + p.getName()));
                }
            }
            document.add(new Paragraph("\nSTAGES: "));
            if (project.getStages() != null) {
                for (Stage stage : project.getStages()) {
                    document.add(new Paragraph("\r\nStage: " + stage.getName() + ", Description: " + stage.getDescription() + ", Duration: " + stage.getDuration()));
                }
            }

            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveAsXML(String xml, String realPath)
            throws IOException {
        FileWriter fw = new FileWriter(realPath);
        fw.write(xml);
        fw.close();
    }

    public void saveAsXSLT(String xml, String realPath)
            throws IOException {
        FileWriter fw = new FileWriter(realPath);
        fw.write(xml);
        fw.close();
    }
}
