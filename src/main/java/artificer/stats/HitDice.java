package main.java.artificer.stats;

public class HitDice {
    private int dice = 0;
    private int number = 0;
    
    public HitDice(String hd) {
        String dn[] = hd.split("d");
        dice = Integer.parseInt(dn[1]);
        number = Integer.parseInt(dn[0]);
        
    }
    
    public int getDice() {
        return dice;
    }
    
    public int getNumber() {
        return number;
    }
    
    public String toString() {
        return number + "d" + dice;
    }

}
