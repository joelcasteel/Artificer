package main.java.artificer.stats;

public class Stat {
    
    public static final String STR = "Strength";
    public static final String DEX = "Dexterity";
    public static final String CON = "Constitution";
    public static final String INT = "Intelligence";
    public static final String WIS = "Wisdom";
    public static final String CHA = "Charisma";
    
    private int stat;
    
    public Stat() {
        stat = 0;
    }
    
    public Stat(int pStat) {
        stat = pStat;
    }
    
    public int getMod() {
        return (stat-10)/2;
    }
    public int getStat() {
        return stat;
    }
    
}
