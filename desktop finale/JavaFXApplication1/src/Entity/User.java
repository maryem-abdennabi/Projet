/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author HP
 */
public class User {
private int idu;
    public String username;
    public String username_canonical;
    public String Email;
    public String Email_canonical;    
    public int enabled;
    public String salt;
     public String password;
    public Date last_login;
    public String confirmation_token;
    public Date  password_requested_at ;
    public String roles;
    public String name;
    public String surname;
    public int number;
    public String photo;
    
    public User() {
    }

    public User(int idu, String username, String username_canonical, String Email, String Email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_token, Date password_requested_at, String roles, String name, String surname, int number, String photo) {
        this.idu = idu;
        this.username = username;
        this.username_canonical = username_canonical;
        this.Email = Email;
        this.Email_canonical = Email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.photo = photo;
    }

    public User(String username, String username_canonical, String Email, String Email_canonical, int enabled, String password, String roles, String name, String surname, int number, String photo) {
       
        this.username = username;
        this.username_canonical = username_canonical;
        this.Email = Email;
        this.Email_canonical = Email_canonical;
        this.enabled = enabled;
        this.password = password;   
        this.roles = roles;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.photo = photo;
    }

    public User(int idu,String username, String username_canonical, String Email, String Email_canonical, String password, int number, String photo) {
        this.idu=idu;
        this.username = username;
        this.username_canonical = username_canonical;
        this.Email = Email;
        this.Email_canonical = Email_canonical;
        this.password = password;
        this.number = number;
        this.photo = photo;
    }
public User(String username, String username_canonical, String Email, String Email_canonical, String password, int number, String photo) {
       
        this.username = username;
        this.username_canonical = username_canonical;
        this.Email = Email;
        this.Email_canonical = Email_canonical;
        this.password = password;
        this.number = number;
        this.photo = photo;
    }
    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail_canonical() {
        return Email_canonical;
    }

    public void setEmail_canonical(String Email_canonical) {
        this.Email_canonical = Email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    
}
