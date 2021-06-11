/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.User;
import Connexion.DataSource;
import Connexion.image;
import GUI.FXMLIdentificationController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
public class UserService  implements IService<User> {
private Connection cnx;
private Statement st;
private PreparedStatement pst;
private ResultSet rs;
    public UserService(){
        cnx=DataSource.getInstance().getCon();
        
    }

    @Override
    public boolean update(User t) {

String req="UPDATE user SET username='"+t.getUsername()+"',username_canonical='"+t.getUsername_canonical()+"',email='"+t.getEmail()+"',email_canonical='"+t.getEmail_canonical()+"',password='"+t.getPassword()+"',num_tel='"+t.getNumber()+"',photo='"+t.getPhoto()+"' WHERE idu='"+t.getIdu()+"'";
try
    {
        st=cnx.createStatement();
        st.executeUpdate(req);
       return true;       
    }
        
       catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
return false;
   }
    public boolean update2(User t,String nom,String prenom) {

String req="UPDATE user SET username='"+t.getUsername()+"',username_canonical='"+t.getUsername_canonical()+"',email='"+t.getEmail()+"',email_canonical='"+t.getEmail_canonical()+"',password='"+t.getPassword()+"',num_tel='"+t.getNumber()+"',photo='"+t.getPhoto()+"' WHERE surname='"+nom+"' and name='"+prenom+"' ";
try
    {
        st=cnx.createStatement();
        st.executeUpdate(req);
       return true;       
    }
        
       catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
return false;
    }
public boolean updatepass(String mail,String pass) {

String req="UPDATE user SET password='"+pass+"' WHERE email='"+mail+"' ";
try
    {
        st=cnx.createStatement();
        st.executeUpdate(req);
       return true;       
    }
        
       catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
return false;
    }

    @Override
    public boolean delete(String email) {
      String req="DELETE FROM user WHERE email='"+email+"' ";
try
    {
        st=cnx.createStatement();
        st.executeUpdate(req);
       return true;       
    }
        
       catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
return false;
    }

    public ResultSet displayAll2() {
    String red="select  username,name,surname,roles,email,photo,num_tel from user where roles!='a:1:{i:0;s:10:\"ROLE_ADMIN\";}'";
    
    try {
        st=cnx.createStatement();
        rs=st.executeQuery(red);
        
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rs;
    }

    public ResultSet displayemail() {
    String red="select email from user where roles!='a:1:{i:0;s:10:\"ROLE_ADMIN\";}' ";
     
    try {
        st=cnx.createStatement();
        rs=st.executeQuery(red);
    
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rs;
    }
    
    @Override
    public ResultSet display(String email) {
try {
        st = cnx.createStatement();
  	String rq="select * from user where Email='"+email+"'";
        rs=st.executeQuery(rq);
}
 catch (SQLException ex) {
        Logger.getLogger(FXMLIdentificationController.class.getName()).log(Level.SEVERE, null, ex);
    }
return rs;
    }

    @Override
    public ResultSet display(int number) {
        try {
        st = cnx.createStatement();
String rq="select * from user where num_tel='"+number+"'";  
 rs=st.executeQuery(rq);
}
 catch (SQLException ex) {
        Logger.getLogger(FXMLIdentificationController.class.getName()).log(Level.SEVERE, null, ex);
    }
return rs;
        
        }

    @Override
    public boolean insert(User u) {

 String req="insert into user (username, username_canonical,email,email_canonical,enabled,password,last_login,roles,name,surname,num_tel,photo) values ('"+u.getUsername()+"','"+u.getUsername_canonical()+"','"+u.getEmail()+"','"+u.getEmail_canonical()+"','"+u.getEnabled()+"','"+u.getPassword()+"',NOW(),'"+u.getRoles()+"','"+u.getName()+"','"+u.getSurname()+"','"+u.getNumber()+"','"+u.getPhoto()+"')";    try
    {
        st=cnx.createStatement();
        st.executeUpdate(req);
        return true;
            }
        
       catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
       }

    return false;
    }

    @Override
    public ResultSet display(String pass, String email) {

 try {
        st = cnx.createStatement();
String rq="select * from user where password="+pass+"' AND email='"+email+"'";  
 rs=st.executeQuery(rq);
}
 catch (SQLException ex) {
        Logger.getLogger(FXMLIdentificationController.class.getName()).log(Level.SEVERE, null, ex);
    }
return rs;
        
        }
    public int number() throws SQLException
    {
        int nb=0;
        String rq="select count(idu) from user ";
    try {
        st = cnx.createStatement();
 rs=st.executeQuery(rq);

    while(rs.next())
    {
      nb=rs.getInt(1);
    }
    }
 catch (SQLException ex) {
        Logger.getLogger(FXMLIdentificationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nb;
    }

    @Override
    public List<User> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            }
