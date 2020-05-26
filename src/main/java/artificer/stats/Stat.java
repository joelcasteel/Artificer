package main.java.artificer.stats;

public class Stat {
    
    public static final String STR = "Strength";
    public static final String DEX = "Dexterity";
    public static final String CON = "Constitution";
    public static final String INT = "Intelligence";
    public static final String WIS = "Wisdom";
    public static final String CHA = "Charisma";
    
    public static final String STATNAME[] = {
        STR, DEX, CON, INT, WIS, CHA
    };
    
    private int score = 0;
    
    public Stat(int pStat) {
        score = pStat;
    }
    
    public int getMod() {
        return (score-10)/2;
    }
    
    public String getModString() {
        String modString = " (";
        if(getMod() >= 0) {
            modString += "+ ";
            modString += Integer.toString(getMod());
        } else {
            modString += "- ";
            modString += Integer.toString(getMod()*-1);
        }
        modString += ")";
        
        return modString;
    }
    public int getScore() {
        return score;
    }
    public String toString() {
        return "" + getScore();
    }
    
}
