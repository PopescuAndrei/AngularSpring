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
public class Partner {
    private String name;
    private boolean leader;

    public Partner(String name, boolean leader) {
        this.name = name;
        this.leader = leader;
    }

    public Partner() {
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Partner{" + "Name=" + name + ", Leader=" + leader + '}';
    }
    
    
}
