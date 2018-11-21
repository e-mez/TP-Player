/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
/**
 *
 * @author uwalakae
 */
public class Player extends Application {
    private Button playBtn, nextBtn, prevBtn, stopBtn, fastForwardBtn, rewindBtn;
    private TilePane lowerTile, higherTile;
    private VBox playControls;
    
    private Button soundEffectsBtn;
    private ToggleButton playlistDisplayBtn;
    private HBox bottomLeftBtns;
    
    private Slider volumeSlider, playSlider;
    
    private Label vlcLabel, playtimeLabel;
    private BorderPane innerTopBorderPane, centerPane, root;
    
    private static final TreeItem<Music> playlist = TreeTableTool.getModel();
    
    private TreeTableView<Music> treeTableView;
    
    private ScrollPane scrollpane;
    
    private BorderPane bottomPane;
    private Button addBtn, randomBtn, loopBtn;
    private HBox bottomLeftBox, bottomCenterBox, bottomRightBox;
    private Label nbElements;
    private TextField searchField;
    
    private BorderPane bottomContainer; 
    
    private Scene playerScene;
    private Stage playerStage;
    
 //   private SplitPane 
    private static boolean toggled = false; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        createRootsLeftChild();  // creates the button controls on the left of the player
                
        createBottomLeftButtons(); // creates the buttons on the bottom left of the player
        
        createInnerTopBorderPane(); // creates the inner border pane with the labels and play slider
        
        createCenterBorderPane(); // creates the CENTER content of main BorderPane
       
        createPlayListSection();
        
        createBottomSection();
        
        initRootPane(); // positions all the contents of the player
        
            
        playerScene = new Scene(root);
    
        primaryStage.setScene(playerScene);
        primaryStage.setTitle("player");
        primaryStage.show();        
        playerStage = primaryStage;
        
        playlistDisplayBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!toggled) 
                    showPlaylist();
                else 
                    removePlaylist();               
            }
        });
        
        root.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (root.getHeight() < 200) {
                    if (toggled) 
                        playlistDisplayBtn.setStyle("-fx-base: white;");
                    
                    root.setBottom(null);
                    toggled = false;
                    playerStage.setMinHeight(100);
                }                    
            }
            
        });
    }
    
    private void createRootsLeftChild() {
        playBtn = new Button(">");
        nextBtn = new Button(">>");
        prevBtn = new Button("<<");
        fastForwardBtn = new Button("|<");
        rewindBtn = new Button(">|");
        stopBtn = new Button("||");
        
        
        lowerTile = new TilePane();
        lowerTile.setPrefTileWidth(40);
        lowerTile.getChildren().addAll(fastForwardBtn, stopBtn, rewindBtn);
        
        higherTile = new TilePane();
        higherTile.setPrefTileWidth(40);
        higherTile.getChildren().addAll(prevBtn, playBtn, nextBtn);
        
        playControls = new VBox(10);
        playControls.setPrefWidth(150);
        playControls.getChildren().addAll(higherTile, lowerTile);
    }
    
    private void createBottomLeftButtons() {
        soundEffectsBtn  = new Button("|||");        
        playlistDisplayBtn = new ToggleButton(":=");
        bottomLeftBtns = new HBox(8);        
        bottomLeftBtns.getChildren().addAll(soundEffectsBtn, playlistDisplayBtn);
    }
    
    private void createInnerTopBorderPane() {
        innerTopBorderPane = new BorderPane();
        vlcLabel = new Label("Lecteur multimédia VLC");
        playtimeLabel = new Label("00:00");
        playSlider = new Slider();
        innerTopBorderPane.setLeft(vlcLabel);
        innerTopBorderPane.setRight(playtimeLabel);
        innerTopBorderPane.setBottom(playSlider);
    }
    
    private void createCenterBorderPane() {
        volumeSlider = new Slider();         
        centerPane = new BorderPane();
        centerPane.setTop(innerTopBorderPane);
        centerPane.setLeft(volumeSlider);
        centerPane.setRight(bottomLeftBtns);
    }
    
    private void initRootPane() {
        bottomContainer = new BorderPane();
        bottomContainer.setTop(scrollpane);
        bottomContainer.setBottom(bottomPane);
        root = new BorderPane();
        root.setPrefWidth(500);
        root.setMinHeight(50);
        root.setLeft(playControls);
        root.setCenter(centerPane);
        root.setBottom(null); 
    }
    
    private void createPlayListSection() {
        // create a TreeTableView with the model contained in the variable "playlist"
        treeTableView = new TreeTableView<>();
        treeTableView.setRoot(playlist);
        treeTableView.setPrefWidth(500);
        treeTableView.setPrefHeight(200);
        
        // add the columns        
        TreeTableColumn nameCol = TreeTableTool.getNameColumn();
        TreeTableColumn auteurCol = TreeTableTool.getAuteurColumn();
        TreeTableColumn dureeCol = TreeTableTool.getDureeColumn();
        
        nameCol.prefWidthProperty().bind(treeTableView.widthProperty().divide(3));
        auteurCol.prefWidthProperty().bind(treeTableView.widthProperty().divide(3));
        dureeCol.prefWidthProperty().bind(treeTableView.widthProperty().divide(3));
        
        treeTableView.getColumns().addAll(nameCol, auteurCol, dureeCol);
       
        scrollpane = new ScrollPane();
        scrollpane.setContent(treeTableView);
    }
    
    private void createBottomSection() {
        bottomPane = new BorderPane();
        bottomPane.setPrefHeight(30);
        addBtn = new Button("+");
        randomBtn = new Button("~");
        loopBtn = new Button("O");
        
        bottomLeftBox = new HBox(10);
        bottomLeftBox.setPrefWidth(100);
        bottomLeftBox.getChildren().addAll(addBtn, randomBtn, loopBtn);
        
        nbElements = new Label("21 éléments");
        bottomCenterBox = new HBox();
        bottomCenterBox.setPrefWidth(300);
        bottomCenterBox.getChildren().add(nbElements);
        bottomCenterBox.setAlignment(Pos.CENTER);
        
        searchField = new TextField();
        bottomRightBox = new HBox(10); // create spacing of length 10 btw contents
        bottomRightBox.setPrefWidth(100);
        bottomRightBox.getChildren().add(searchField);
        
        bottomPane.setLeft(bottomLeftBox);
        bottomPane.setCenter(bottomCenterBox);
        bottomPane.setRight(bottomRightBox);
    }
    
    private void removePlaylist() {
        playlistDisplayBtn.setStyle("-fx-base: white;");
        root.setBottom(null); 
        playerStage.setHeight(100);
        toggled = false;
    }
    
    private void showPlaylist() {
        playlistDisplayBtn.setStyle("-fx-base: blue;");
        root.setBottom(bottomContainer);
        playerStage.setHeight(350);
        toggled = true;
    }
    
}
