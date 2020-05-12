package main.java.artificer.stats;

public class Stat {
    
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
