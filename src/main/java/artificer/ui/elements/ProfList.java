package main.java.artificer.ui.elements;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.Proficiency;

/**
 * Custom ListView for Holding Proficiencies via ProfCells
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class ProfList {
    //We have the ListView
    private ListView<Proficiency> profList = new ListView<Proficiency>();
    
    //We also have an observable list to manage.
    private ObservableList<Proficiency> obvsList =  FXCollections.observableArrayList();
    
    
    /**
     * Construct a new ProfList.s
     */
    public ProfList() {
        
        //This is so we can pass this object to the listCellFactory
        ProfList holder = this;
        
        profList.getStyleClass().add("prof-list");
        profList.setPrefSize(300, 180);
        profList.setCellFactory(new Callback<ListView<Proficiency>, ListCell<Proficiency>>(){
            @Override
            public ListCell<Proficiency> call(ListView<Proficiency> monsterListView) {
                return new ProfCell(holder);
                
            }
        });
        
        profList.setItems(obvsList);
        
        
    }
    
    /**
     * (Probable could have done this better with inheritance)
     * @return The actual ListView Node. This is for displaying purposes
     */
    public ListView<Proficiency> getProfList() {
        return profList;
    }
    
    /**
     * Set the initial content of the ProfList
     * 
     * @param source The Monster to take profs from
     */
    public void setContent(Monster source) {
        Iterator<Proficiency> iter = source.getStats().getAllProfs().iterator();
        //mainList = new ArrayList<>();
        obvsList = FXCollections.observableArrayList();
        while(iter.hasNext()) {
            Proficiency aProf = iter.next();
            obvsList.add(aProf.copyProficiency());
            
        }
        profList.setItems(obvsList);
        
    }
    
    /**
     * 
     * @param prof The prof to remove
     */
    public void removeContent(Proficiency prof) {
        obvsList.remove(prof);
    }
    
    public Iterator<Proficiency> getCurrentContent() {
        return obvsList.iterator();
    }
    
    /**
     * 
     * @param prof The prof to add
     */
    public void addContent(Proficiency prof) {
        obvsList.add(prof);
    }
    
    
    /**
     * Replace a prof with a new one.
     * Useful for editing profs while maintaining our list.
     * 
     * @param prof The old proficiency
     * @param newProf The proficiency we want to replace it with
     */
    public void switchContent(Proficiency prof, Proficiency newProf) {
        int idx = obvsList.indexOf(prof);
        obvsList.set(idx, newProf);
    }
    
    
}
