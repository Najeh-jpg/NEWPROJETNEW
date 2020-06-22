package com.example.android.recycleview.Model;

public class User {
    private String uid;
    private String mail;
    private String nom;
    private String prenom;
    private String url;
    private String token;

    private int admin = 0 ;


    public User() {
    }

    public User(String uid, String mail, String nom, String prenom, String url, String token, int admin) {
        this.uid = uid;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.url = url;
        this.token = token;
        this.admin = admin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

}