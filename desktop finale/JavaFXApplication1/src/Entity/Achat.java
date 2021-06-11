/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ibrahim
 */
public class Achat {
    private SimpleIntegerProperty id_achat;	
    private SimpleStringProperty client_name;	
    private SimpleStringProperty client_type;	
    private SimpleStringProperty client_address;	
    private SimpleFloatProperty quantite;
    private SimpleIntegerProperty  etat;
    private SimpleStringProperty date;

    
     

    public int getId_achat() {
        return id_achat.get();
    }
   

   

    public String getClient_name() {
        return client_name.get();
    }

    public String getClient_type() {
        return client_type.get();
    }

    public String getClient_address() {
        return client_address.get();
    }

    public float getQuantite() {
        return quantite.get();
    }

    public int getEtat() {
        return etat.get();
    }

    public void setId_achat(int id_achat) {
        this.id_achat.set(id_achat);
    }

    public Achat(String client_name, String client_type, String client_address, float quantite, int etat,String date) {
       this.client_name=new SimpleStringProperty(client_name);
        this.client_type=new SimpleStringProperty(client_type);
        this.client_address=new SimpleStringProperty(client_address);
        this.quantite=new SimpleFloatProperty(quantite);
        this.etat=new SimpleIntegerProperty(etat);
        this.date=new SimpleStringProperty(date);
    }

    public void setClient_name(String client_name) {
        this.client_name.set(client_name);
    }

    public void setClient_type(String client_type) {
        this.client_type.set(client_type);
    }

    public void setClient_address(String client_address) {
        this.client_address.set(client_address);;
    }

    public void setQuantite(float quantite) {
        this.quantite.set(quantite);;
    }

    public void setEtat(int etat) {
        this.etat.set(etat);
    }

    public Achat(int id_achat, String client_name, String client_type, String client_address, float quantite, int etat,String date) {
        this.id_achat=new SimpleIntegerProperty(id_achat);
        this.client_name=new SimpleStringProperty(client_name);
        this.client_type=new SimpleStringProperty(client_type);
        this.client_address=new SimpleStringProperty(client_address);
        this.quantite=new SimpleFloatProperty(quantite);
        this.etat=new SimpleIntegerProperty(etat);
        this.date=new SimpleStringProperty(date);

    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
  
    public Achat(String client_name, String client_type, String client_address) {
        this.client_name=new SimpleStringProperty(client_name);
        this.client_type=new SimpleStringProperty(client_type);
        this.client_address=new SimpleStringProperty(client_address);
    }

    @Override
    public String toString() {
        return "Achat{" + "id_achat=" + id_achat.get() +  ", client_name=" + client_name.get() + ", client_type=" + client_type.get() + ", client_address=" + client_address.get() + ", quantite=" + quantite.get() + ", etat=" + etat.get() + ", Date=" + date.get() + '}';
    }
    public Achat(){
        
    }
    
    

    
    
}
