/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ibrahim
 */
public class Entrepot {

    private SimpleIntegerProperty id_entrepot;
    private SimpleStringProperty address;
    private SimpleIntegerProperty nbrRangs;
    private SimpleStringProperty phone;
    private SimpleStringProperty phone_bis;
    private SimpleStringProperty latitude;
    private SimpleStringProperty longitude;
    
    public Entrepot(int id_entrepot, String address, int nbrRangs, String phone, String phone_bis, String latitude, String longitude) {
        this.id_entrepot = new SimpleIntegerProperty(id_entrepot);
        this.address = new SimpleStringProperty(address);
        this.nbrRangs = new SimpleIntegerProperty(nbrRangs);
        this.phone = new SimpleStringProperty(phone);
        this.phone_bis = new SimpleStringProperty(phone_bis);
        this.latitude = new SimpleStringProperty(latitude);
        this.longitude = new SimpleStringProperty(longitude);

    }

    public String getLatitude() {
        return latitude.get();
    }

    public void setLatitude(String latitude) {
        this.latitude.set(latitude);
    }

    public String getLongitude() {
        return longitude.get();
    }

    public void setLongitude(String longitude) {
        this.longitude.set(longitude);
    }

    public Entrepot(String address, int nbrRangs, String phone, String phone_bis, String latitude, String longitude) {
        this.address = new SimpleStringProperty(address);
        this.nbrRangs = new SimpleIntegerProperty(nbrRangs);
        this.phone = new SimpleStringProperty(phone);
        this.phone_bis = new SimpleStringProperty(phone_bis);
        this.latitude = new SimpleStringProperty(latitude);
        this.longitude = new SimpleStringProperty(longitude);

    }

    public int getId_entrepot() {
        return id_entrepot.get();
    }

    public void setId_entrepot(int id_entrepot) {
        this.id_entrepot.set(id_entrepot);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public int getNbrRangs() {
        return nbrRangs.get();
    }

    public void setNbrRangs(int nbrRangs) {
        this.nbrRangs.set(nbrRangs);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getPhone_bis() {
        return phone_bis.get();
    }

    public void setPhone_bis(String phone_bis) {
        this.phone_bis.set(phone_bis);
    }

    @Override
    public String toString() {
      
        return   id_entrepot.get()+" "+ address.get() + "";

    }


 public String toPdf() {
        return "Entrepot" +""+"id_entrepot=" + id_entrepot.get() +"\n"+"\n"+" address=" + address.get() + ", nbrRangs=" + nbrRangs.get() + ", phone=" + phone.get() + ", phone_bis=" + phone_bis.get() +"\n"+"\n";
    }
}
