/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.semanticapp.model;

/**
 *
 * @author Vlad
 */
public class Stage {
    private String name;
    private int duration;
    private String description;

    public Stage(String name, int duration, String description) {
        this.name = name;
        this.duration = duration;
        this.description = description;
    }

    public Stage() {
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Stage{" + "name=" + name + ", duration=" + duration + ", description=" + description + '}';
    }
    
    
}
