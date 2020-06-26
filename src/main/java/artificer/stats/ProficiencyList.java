package main.java.artificer.stats;

import java.util.ArrayList;
import com.google.gson.JsonArray;

import main.java.artificer.Debugger;

public class ProficiencyList {
    private ArrayList<Proficiency> profs;
    
    public ProficiencyList() {
        profs = new ArrayList<>();
    }
    
    public ProficiencyList(JsonArray source) {
        profs = new ArrayList<>();
        try {
            for(int i = 0; i < source.size(); i++) {
                profs.add(new Proficiency(source.get(i).getAsJsonObject()));
            }
            
        } catch (Exception ex) {
            Debugger.debug("Error De-Serializing ProficiencyList: " + ex.getMessage());
        }
    }
    
    public Proficiency getProf(int i) {
        return profs.get(i);
    }
    
    public Iterable<Proficiency> getAll() {
        return profs;
    }
    
    public void addProf(Proficiency prof) {
        profs.add(prof);
    }
    
    public void removeProf(Proficiency prof) {
        profs.remove(prof);
    }
    
    public ProficiencyList deepCopy() {
        ProficiencyList copy = new ProficiencyList();
        for(Proficiency p : profs) {
            copy.addProf(p.copyProficiency());
        }
        return copy;
    }
    
    public JsonArray getAsJson() {
        JsonArray jProfs = new JsonArray();
        for(Proficiency p : profs) {
            jProfs.add(p.toJson());
        }
        return jProfs;
    }

}
