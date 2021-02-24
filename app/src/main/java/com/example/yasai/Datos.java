package com.example.yasai;

public class Datos {
    /*DATO IMPORTANTE:los metodos setter y getter no deben tener el nombre de la propiedad
    * sino dará error.como,no entendi? es decir si la propiedad de clase se llama origen el getter y setter
    * no debe tener como nombre getOrigen porque contiene la palabra "origen" y hay conflicto.
    * Este es un fallo que solo se da cuando trabajamos con firebase*/
    public String Nombre;
    public String Descripcion;
    public String Imagen;
    public String Precio;
    public String cantidad;
    //public int id;
    public static int id=0;
    int idsiguiente=id;

    public Datos(){
        //Constructor por defecto
    }

    public Datos(String nom,String desc,String Img){
        this.Nombre=nom;
        this.Descripcion=desc;
        this.Imagen=Img;
    }

    public Datos(String nombre,String precio,String cantidad_objetos_comprados,int id){
        this.Nombre=nombre;
        this.Precio=precio;
        this.cantidad=cantidad_objetos_comprados;
        this.id=idsiguiente;
        id++;
    }

    public Datos(String nombre,String precio,String cantidad_objetos_comprados,Object o){
        this.Nombre=nombre;
        this.Precio=precio;
        this.cantidad=cantidad_objetos_comprados;
    }

    public String getNom() {
        return Nombre;
    }
    public void setNom(String nom) {
        this.Nombre = nom;
    }
    public String getDesc() {
        return Descripcion;
    }
    public void setDesc(String desc) { Descripcion = desc; }
    public String getImg() {
        return Imagen;
    }
    public void setImg(String img) {
        Imagen=img;
    }
    public String getPreci() {
        return Precio;
    }
    public void setPreci(String precio) {
        Precio = precio;
    }
    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
