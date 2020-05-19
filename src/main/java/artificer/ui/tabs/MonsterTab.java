package main.java.artificer.ui.tabs;



import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.artificer.ui.SearchBox;

public class MonsterTab extends Tab {
    
    private HBox content = new HBox();
    
    private VBox searchBox = new SearchBox(null);
    
    private TextArea detailsText = new TextArea();
    private Button clearButton = new Button();
    
    public MonsterTab() {
        setText("Monsters");
        setClosable(false);
        
        Image icon = new Image("main/resources/ui/icons/monster.png");
        setGraphic(new ImageView(icon));

        content.setPadding(new Insets(36, 48, 36, 48));
        content.setSpacing(60);
        
        content.getChildren().add(searchBox);
        
        VBox detailBox = new VBox();
        detailBox.setSpacing(12);
        detailsText.setPrefSize(600, 540);
        detailBox.getChildren().add(detailsText);
        clearButton.setText("Clear");
        detailBox.getChildren().add(clearButton);
        
        
        content.getChildren().add(detailBox);
        
        setContent(content);
    }
}
