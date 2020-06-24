package main.java.artificer.stats;

import com.google.gson.JsonObject;

/**
 * Hold the data required for HD in this class.
 * 
 * @author Joel Casteel
 * @version May 2020
 */
public class HitDice {
    
    //Primitive Fields
    private int dice = 0;
    private int number = 0;
    
    /**
     * Construct a new HD object from a String.
     * This will generally come from a JSON source.
     * 
     * @param hd String in the format "<int>d<int>"
     */
    public HitDice(String hd) {
        
        String dn[] = hd.split("d");
        dice = Integer.parseInt(dn[1]);
        number = Integer.parseInt(dn[0]);
        
    }
    
    public HitDice(int pDice, int pNum) {
        dice = pDice;
        number = pNum;
    }
    
    public HitDice copyHitDice() {
        return new HitDice(dice, number);
    }
    
    /**
     * 
     * @return The type of Dice (d6, d8, d10, d12)
     */
    public int getDice() {
        return dice;
    }
    
    /**
     * 
     * @return Return the number of dice.
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Construct a String representation of the HD object.
     * 
     * Funny enough, you could pass toString() into the constructor to create a
     * "deep copy" of a HD. Not like that's very useful... maybe I'll be wrong though.
     */
    public String toString() {
        return number + "d" + dice;
    }
    

}
