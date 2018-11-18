/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

/**
 *
 * @author Adelle
 */

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import java.util.Random;
import java.util.ArrayList;

public class TreeTableTool {
    @SuppressWarnings("unchecked")
    public static TreeItem<Music> getModel() {
        // create all the music in the playlist
        ArrayList<Music> playlist = new ArrayList<Music>();
        for (int i = 1; i < 22; i++) {
            String nom, auteur, duree;
            nom = "Music" + String.valueOf(i - 1);
            auteur = "Auteur" + String.valueOf(i - 1);
            duree = "0";
            
            // random music time generator ;)
            Random random = new Random();
            int random2nd, random3rd, random4th;
            random2nd = random.nextInt(6);
            random3rd = random.nextInt(6);
            random4th = random.nextInt(10);
            duree += String.valueOf(random2nd) + ":" + String.valueOf(random3rd) + String.valueOf(random4th);
            
            Music music = new Music(nom, auteur, duree);
            playlist.add(music);         
        }
        
        // build the tree nodes
        TreeItem<Music> music2Node = new TreeItem<>(playlist.get(2));
        music2Node.getChildren().addAll(new TreeItem<>(playlist.get(6)), new TreeItem<>(playlist.get(7)), new TreeItem<>(playlist.get(8)), new TreeItem<>(playlist.get(18)));
        
        TreeItem<Music> music3Node = new TreeItem<>(playlist.get(3));
        music3Node.getChildren().addAll(new TreeItem<>(playlist.get(9)), new TreeItem<>(playlist.get(10)), new TreeItem<>(playlist.get(11)), new TreeItem<>(playlist.get(19)));
        
        TreeItem<Music> music4Node = new TreeItem<>(playlist.get(4));
        music4Node.getChildren().addAll(new TreeItem<>(playlist.get(12)), new TreeItem<>(playlist.get(13)), new TreeItem<>(playlist.get(14)), new TreeItem<>(playlist.get(20)));
        
        TreeItem<Music> music5Node = new TreeItem<>(playlist.get(5));
        music5Node.getChildren().addAll(new TreeItem<>(playlist.get(15)), new TreeItem<>(playlist.get(16)), new TreeItem<>(playlist.get(17)));
        
        TreeItem<Music> music1Node = new TreeItem<>(playlist.get(0));
        music1Node.getChildren().addAll(new TreeItem<>(playlist.get(1)), music2Node, music3Node, music4Node, music5Node);
        System.out.println(music1Node.getValue().getNom());
        return music1Node;
    }
    
    // music name TreeTableColumn
    public static TreeTableColumn<Music, String> getNameColumn() {
        TreeTableColumn<Music, String> nameColumn = new TreeTableColumn<>("Nom");
        nameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        return nameColumn;
    }
    
    // auteur TreeTableColumn
    public static TreeTableColumn<Music, String> getAuteurColumn() {
        TreeTableColumn<Music, String> auteurColumn = new TreeTableColumn<>("Auteur");
        auteurColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("auteur"));
        return auteurColumn;
    }
    
    // duree TreeTableColumn
    public static TreeTableColumn<Music, String> getDureeColumn() {
        TreeTableColumn<Music, String> dureeColumn = new TreeTableColumn<>("Duree");
        dureeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("duree"));
        return dureeColumn;
    }
        
}
