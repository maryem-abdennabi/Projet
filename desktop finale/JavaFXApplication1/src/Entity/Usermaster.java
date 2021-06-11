/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Usermaster {
public ObjectProperty userPhoto = new SimpleObjectProperty();
public SimpleStringProperty userName = new SimpleStringProperty(); 
public SimpleStringProperty userfname = new SimpleStringProperty(); 
public SimpleStringProperty userPassword = new SimpleStringProperty();
public SimpleStringProperty userType = new SimpleStringProperty();
public SimpleIntegerProperty number = new SimpleIntegerProperty();
public SimpleStringProperty mail = new SimpleStringProperty();

    public Object getUserPhoto() {
        return userPhoto.get();
    }

    public void setUserPhoto(ObjectProperty userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(SimpleStringProperty userName) {
        this.userName = userName;
    }

    public String getUserfname() {
        return userfname.get();
    }

    public void setUserfname(SimpleStringProperty userfname) {
        this.userfname = userfname;
    }

    public String getUserPassword() {
        return userPassword.get();
    }

    public void setUserPassword(SimpleStringProperty userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType.get();
    }

    public void setUserType(SimpleStringProperty userType) {
        this.userType = userType;
    }

    public Integer getNumber() {
        return number.get();
    }

    public void setNumber(SimpleIntegerProperty number) {
        this.number = number;
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(SimpleStringProperty mail) {
        this.mail = mail;
    }
 
}
