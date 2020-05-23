package main.java.artificer.ui.detail;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Detail extends BorderPane {
    protected boolean open;
    protected ScrollPane layout;
    protected HBox header;
    protected Label headerLabel;
    protected Button backButton;
    protected VBox holder;
    
    public Detail() {
        getStylesheets().add(getClass().getResource("/ui/stylesheets/side-menu.css").toString());
        getStylesheets().add(getClass().getResource("/ui/stylesheets/monster-details.css").toString());
        getStyleClass().add("root");
        
        
        
        layout = new ScrollPane();
        layout.getStyleClass().add("scroll-pane");
        
        header = new HBox();
        header.setSpacing(12);
        header.setAlignment(Pos.CENTER_LEFT);
        
        headerLabel = new Label();
        backButton = new Button("<-");
        
        header.getChildren().addAll(backButton, headerLabel);
        setTop(header);
        
        
        
        holder = new VBox();
        holder.setPadding(new Insets(24, 0, 0, 0));
        holder.setSpacing(12);
        holder.getStyleClass().add("pane");
        layout.setContent(holder);
        
        open = false;
        setVisible(open);
        setManaged(open);
        
        setPrefWidth(360);
        
        setCenter(layout);
    }
    
    public boolean isOpen() {
        return open;
    }
    
    public void toggle() {
        open = !open;
        setVisible(open);
        setManaged(open);
    }
    
    public void close() {
        open = false;
        setVisible(open);
        setManaged(open);
    }
    
    public void open() {
        open = true;
        setVisible(open);
        setManaged(open);
    }
    
    class DoubleEntry extends HBox {
        TextField nameField;
        TextField detailField;
        
        DoubleEntry(String entry) {
            
            String splits[] = entry.split(":");
            
            nameField = new TextField();
            detailField = new TextField();
        }
    }
    
    class NamedField extends GridPane {
        Label name;
        Node value;
        
        NamedField(String pName) {
            getColumnConstraints().add(new ColumnConstraints(80));
            getColumnConstraints().add(new ColumnConstraints(120));
            name = new Label(pName);
            value = new TextField();
            add(name, 0, 0);
            add(value, 1, 0);
        }
        
        Node getNode() {
            return value;
        }
        
        
    }
    
    
}
