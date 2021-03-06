package com.example.yasai;

public class Receta {
    public String nom;
    public String descripcion;
    public String url;

    public Receta(){
    }
    public Receta(String nom,String descripcion,String url) {
        this.nom = nom;
        this.descripcion=descripcion;
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
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
