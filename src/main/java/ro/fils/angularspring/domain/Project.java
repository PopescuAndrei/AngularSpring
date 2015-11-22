/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.semanticapp.model;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Project {
    
    private String id;
    private String title;
    private int budget;
    private int duration;
    private ArrayList<Partner> partners;
    private String domain;
    private String objective;
    private ArrayList<Stage> stages;

    public Project() {
    }

    public Project(String id, String title, int budget, int duration, ArrayList<Partner> partners, String domain, String objective, ArrayList<Stage> stages) {
        this.id = id;
        this.title = title;
        this.budget = budget;
        this.duration = duration;
        this.partners = partners;
        this.domain = domain;
        this.objective = objective;
        this.stages = stages;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Partner> getPartners() {
        return partners;
    }

    public void setPartners(ArrayList<Partner> partners) {
        this.partners = partners;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", title=" + title + ", budget=" + budget + ", duration=" + duration + ", partners=" + partners + ", domain=" + domain + ", objective=" + objective + ", stages=" + stages + '}';
    }
    
    
}
