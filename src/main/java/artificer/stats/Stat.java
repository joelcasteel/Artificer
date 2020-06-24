package main.java.artificer.stats;

/**
 * Hold all the data surrounding a monster's stat in this class.
 * A very meat-and-potatoes kinda object.
 * 
 * @author Joel Casteel
 * @version May 2020
 *
 */
public class Stat {
    
    //The final stats that hold the names of kinds of stats.
    //Now, why'd I put them here instead of in Statblock?
    //A) It's prettier to say Stat.STR that StatBlock.STR.
    //B) It makes more logical sense to me too.
    public static final String STR = "strength";
    public static final String DEX = "dexterity";
    public static final String CON = "constitution";
    public static final String INT = "intelligence";
    public static final String WIS = "wisdom";
    public static final String CHA = "charisma";
    
    //This array contains the names of the Stats in order.
    public static final String STATNAME[] = {
        STR, DEX, CON, INT, WIS, CHA
    };
    
    
    //The score of a stat
    private int score = 0;
    
    /**
     * Create a new Stat from a score.
     * 
     * @param pStat The score
     */
    public Stat(int pStat) {
        score = pStat;
    }
    
    public Stat copyStat() {
        return new Stat(score);
    }
    
    /**
     * 
     * @return The modifier tied to a stat.
     */
    public int getMod() {
        return (score-10)/2;
    }
    
    /**
     * Return the string representation of the modifier of a stat.
     * (+) 0 >=
     * (-) 0 <
     * @return The string representation of a stat 
     */
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
    /**
     * 
     * @return The score of a stat.
     */
    public int getScore() {
        return score;
    }
    
    /**
     * @return The string rep of the score (not mod)
     */
    public String toString() {
        return "" + getScore();
    }
    
}
