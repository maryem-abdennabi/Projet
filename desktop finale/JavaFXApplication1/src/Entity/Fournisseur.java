/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ibrahim
 */
public class Fournisseur {
    private SimpleIntegerProperty id;
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private SimpleStringProperty email;
    

    public Fournisseur(String firstname, String lastname, String phoneNumber, String address, String email) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
    }

    public Fournisseur(int id_fournisseur, String firstname, String lastname, String phoneNumber, String address, String email) {
        this.id = new SimpleIntegerProperty(id_fournisseur);
         this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
    }

    public int getId_fournisseur() {
        return id.get();
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id.set(id_fournisseur);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

   
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address); 
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return firstname.get()+" "+lastname.get();
    }
    
    
}
