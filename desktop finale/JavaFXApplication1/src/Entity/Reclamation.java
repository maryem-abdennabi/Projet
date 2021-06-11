/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Ahmed
 */
public class Reclamation {
    private int id_reclamation;
    private int id_product;
    private int id_user;
    private String username;
    private String mail; 
    private String Contenu;
    private Date Date_rec;
    private String etat;
    private int priority;

    public Reclamation(int id_reclamation, int id_product, int id_user, String username, String mail, String Contenu, Date Date_rec, String etat, int priority) {
        this.id_reclamation = id_reclamation;
        this.id_product = id_product;
        this.id_user = id_user;
        this.username = username;
        this.mail = mail;
        this.Contenu = Contenu;
        this.Date_rec = Date_rec;
        this.etat = etat;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Reclamation(int id_product, int id_user, String username, String mail, String Contenu, Date Date_rec, String etat, int priority) {
        this.id_product = id_product;
        this.id_user = id_user;
        this.username = username;
        this.mail = mail;
        this.Contenu = Contenu;
        this.Date_rec = Date_rec;
        this.etat = etat;
        this.priority = priority;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    

    public Reclamation( int id_product, int id_user, String Contenu, Date Date_rec, String etat) {
        this.id_product = id_product;
        this.id_user = id_user;
        this.Contenu = Contenu;
        this.Date_rec = Date_rec;
        this.etat = etat;
    }

    public Reclamation() {
    }

    public Reclamation(int id_reclamation, int id_product, int id_user, String Contenu, Date Date_rec, String etat) {
        this.id_reclamation = id_reclamation;
        this.id_product = id_product;
        this.id_user = id_user;
        this.Contenu = Contenu;
        this.Date_rec = Date_rec;
        this.etat = etat;

    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

   

   
    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

   

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDate_rec() {
        return Date_rec;
    }

    public void setDate_rec(Date Date_rec) {
        this.Date_rec = Date_rec;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", id_product=" + id_product + ", id_user=" + id_user + ", Contenu=" + Contenu + ", Date_rec=" + Date_rec + ", etat=" + etat + '}';
    }


    
    

    
    
}
