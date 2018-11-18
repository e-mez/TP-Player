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
public class Music {
    private String nom;
    private String auteur;
    private String duree;

    public Music(String nom, String auteur, String duree) {
        this.nom = nom;
        this.auteur = auteur;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getDuree() {
        return duree;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
    
}
