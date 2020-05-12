package main.java.artificer.stats;

public class Proficiency {
    private String name;
    private int mod;
    
    Proficiency() {
        name = "Nothing";
        mod = 0;
    }
    
    Proficiency(String pName, int pMod) {
        name = pName;
        mod = pMod;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMod() {
        return mod;
    }
}
