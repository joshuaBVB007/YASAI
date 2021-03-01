package com.example.yasai;

public class Receta {
    public String nom;
    public String url;

    public Receta(){
    }
    public Receta(String nom, String url) {
        this.nom = nom;
        this.url=url;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getUrl() { return url; }
    public void setUrl(String url) {
        this.url = url;
    }
}
