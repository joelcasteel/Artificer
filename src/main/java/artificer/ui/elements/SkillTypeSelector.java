package main.java.artificer.ui.elements;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * Custom ComboBox for selecting Skill/Save type for proficiencies
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class SkillTypeSelector extends ComboBox<String> {
    
    private ImageView saveIcon 
        = new ImageView(new Image(getClass().getResource("/ui/icons/save.png").toString()));
    
    private ImageView skillIcon 
        = new ImageView(new Image(getClass().getResource("/ui/icons/skill.png").toString()));
    
    /**
     * Construct a new Skill Selector
     */
    public SkillTypeSelector() {
        setItems(FXCollections.observableArrayList("Skill", "Save"));
        getStyleClass().add("cool-combo-box");
        
        setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    
                    Label label;
                    {
                        getStyleClass().add("cool-combo-cell");
                        label = new Label();
                        
                    }

                    @Override protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            label.setText(item);
                            if(item.contentEquals("Skill")) {
                                label.setGraphic(skillIcon);
                            } else if (item.contentEquals("Save")) {
                                label.setGraphic(saveIcon);
                            }
                            setGraphic(label);
                            
                        }
                   }
              };
          }
        });
        
        setButtonCell(new IconTextCellClass());
        
        getSelectionModel().selectFirst();
        
        
    }
    
    /**
     * Custom ListCell for the "Button-like" element of the ComboBox
     * @author joel
     *
     */
    class IconTextCellClass extends ListCell<String> {
        
        private ImageView saveIconButton 
            = new ImageView(new Image(getClass().getResource("/ui/icons/save.png").toString()));
        private ImageView skillIconButton 
            = new ImageView(new Image(getClass().getResource("/ui/icons/skill.png").toString()));
        
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if(item.contentEquals("Skill")) {
                    setGraphic(skillIconButton);
                } else if (item.contentEquals("Save")) {
                    setGraphic(saveIconButton);
                }
            }
        }
    };
}
