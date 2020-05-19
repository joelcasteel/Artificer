package main.java.artificer.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Monster {
    
    StatBlock stats;
    
    
    String name, size, type, alignment, hit_dice;
    int armor_class, hit_points;
    int strength, dexterity, constitution,
        intelligence, wisdom, charisma;
    Map<String, String> speed;
    List<Proficiency> proficiencies;
    String damage_vulnerabilities[];
    String damage_resistances[];
    String damage_immunities[];
    List<Condition> condition_immunities;
    Map<String, String> senses;
    String languages;
    int challenge_rating;
    
    public Monster() {
        System.out.println("Hello There!!!!");
    }
    
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public String getHit_dice() {
        return hit_dice;
    }
    public void setHit_dice(String hit_dice) {
        this.hit_dice = hit_dice;
    }
    public int getArmor_class() {
        return armor_class;
    }
    public void setArmor_class(int armor_class) {
        this.armor_class = armor_class;
    }
    public int getHit_points() {
        return hit_points;
    }
    public void setHit_points(int hit_points) {
        this.hit_points = hit_points;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getConstitution() {
        return constitution;
    }
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public int getWisdom() {
        return wisdom;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    public int getCharisma() {
        return charisma;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public String getLanguages() {
        return languages;
    }
    public void setLanguages(String languages) {
        this.languages = languages;
    }
    public int getChallenge_rating() {
        return challenge_rating;
    }
    public void setChallenge_rating(int challenge_rating) {
        this.challenge_rating = challenge_rating;
    }
    public Map<String, String> getSpeed() {
        return speed;
    }
    public StatBlock getStats() {
        return stats;
    }
    public void setStats(StatBlock block) {
        stats = block;
    }
    public List<Proficiency> getProficiencies() {
        return proficiencies;
    }
    public String[] getDamage_vulnerabilities() {
        return damage_vulnerabilities;
    }
    public String[] getDamage_resistances() {
        return damage_resistances;
    }
    public String[] getDamage_immunities() {
        return damage_immunities;
    }
    public List<Condition> getCondition_immunities() {
        return condition_immunities;
    }
    public Map<String, String> getSenses() {
        return senses;
    }
    
    
    
   
}
