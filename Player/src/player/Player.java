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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
/**
 *
 * @author uwalakae
 */
public class Player extends Application {
    Button playBtn, nextBtn, prevBtn, stopBtn, fastForwardBtn, rewindBtn;
    TilePane lowerTile, higherTile;
    VBox playControls;
    
    private Button soundEffectsBtn;
    private ToggleButton playlistDisplayBtn;
    private HBox bottomLeftBtns;
    
    private Slider volumeSlider, playSlider;
    
    private Label vlcLabel, playtimeLabel;
    private BorderPane innerTopBorderPane, centerPane, root;
    
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
       
        initRootPane(); // positions all the contents of the player
        
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("player");
        primaryStage.show();        
        
        playlistDisplayBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!toggled) {
                    playlistDisplayBtn.setStyle("-fx-base: blue;");
                    toggled = true;
                }
                else {
                    playlistDisplayBtn.setStyle("-fx-base: white;");
                    toggled = false;
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
        root = new BorderPane();        
        root.setLeft(playControls);
        root.setCenter(centerPane);
    }
    
    private void createPlayListSection() {
        
    }
    
}
