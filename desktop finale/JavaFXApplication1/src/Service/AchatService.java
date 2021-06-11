/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Connexion.DataSource;
import Entity.Achat;
import Entity.Fournisseur;
import Entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ibrahim
 */
public class AchatService implements IService<Achat> {

    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;

    public AchatService() {
        this.cnx = DataSource.getInstance().getCon();
    }

    @Override
    public boolean insert(Achat t) {
        String sql = "insert into achat (client_name,client_type,client_address,quantite,etat,date) values (?,?,?,?,?,?)";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, t.getClient_name());
            pst.setString(2, t.getClient_type());
            pst.setString(3, t.getClient_address());
            pst.setFloat(4, t.getQuantite());
            pst.setInt(5, t.getEtat());
            pst.setString(6, t.getDate());

            pst.executeUpdate();
            System.out.println("Achat added successfully");

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    public boolean createAchat(Achat a){
         String sql = "insert into achat (client_name,client_type,client_address,quantite,etat,unite) values (?,?,?,?,?,?)";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, a.getClient_name());
            pst.setString(2, a.getClient_type());
            pst.setString(3, a.getClient_address());
            pst.setFloat(4, 0);
            pst.setInt(5, 1);
            pst.setString(6, "");

            pst.executeUpdate();
            System.out.println("Achat added successfully");

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean update(Achat t) {
        String sql = "update achat set  client_name= ? , client_type= ? , client_address= ? , etat = ? , date= ? where id= ?";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, t.getClient_name());
            pst.setString(2, t.getClient_type());
            pst.setString(3, t.getClient_address());
            pst.setInt(4, t.getEtat());
            pst.setString(5, t.getDate());
            pst.setInt(6, t.getId_achat());

            pst.executeUpdate();
            System.out.println("Achat updated");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    public boolean updateEtat(int etat,int id) {
        String sql = "update achat set etat = ? where id= ?";
        try {
            pst = cnx.prepareCall(sql);
            
            pst.setInt(1, etat);
            pst.setInt(2, id);

            pst.executeUpdate();
            System.out.println("Achat valider");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean delete(int id) {
 String sql = "delete from achat where id= ?";

        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            System.out.println("achat deleted");
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
            return false;
        }    }
    

    @Override
    public List<Achat> displayAll() {
String sql="Select* from achat";
        List<Achat> list=new ArrayList<>();
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                list.add(new Achat(res.getInt("id"),res.getString("client_name"),res.getString("client_type"),res.getString("client_address"),res.getFloat("quantite"),res.getInt("etat"),res.getString("date")));
            }
            return list;
        } catch (SQLException ex) {
                        Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;    }
    public List<Achat> displayAchatNonValide(){
        String sql="Select* from achat where etat=4";
        List<Achat> list=new ArrayList<>();
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                list.add(new Achat(res.getInt("id"),res.getString("client_name"),res.getString("client_type"),res.getString("client_address"),res.getFloat("quantite"),res.getInt("etat"),res.getString("date")));
            }
            return list;
        } catch (SQLException ex) {
                        Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;
    }
    
     public Achat findById(int id) {
        String sql="Select* from achat where id="+id+";";
        Achat a=null;
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()){
 int a1 = (int) Math.round(res.getFloat(6));

                a= new Achat(res.getInt(1),res.getString(3),res.getString(4),res.getString(5),a1,res.getInt(7),res.getString(2));            }
            return a; 
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public Achat findByClientType(String ref){
         String sql="Select* from achat where client_type='"+ref+"' and etat=3 ;";
        Achat a=null;
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()){
                int a1 = (int) Math.round(res.getFloat(6));

                a= new Achat(res.getInt(1),res.getString(3),res.getString(4),res.getString(5),a1,res.getInt(7),res.getString(2));
            }
            return a; 
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     public Achat findByAddress(String ref){
         String sql="Select* from achat where client_type='"+ref+"' and etat=3 ;";
        Achat a=null;
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()){
                int a1 = (int) Math.round(res.getFloat(6));

                a= new Achat(res.getInt(1),res.getString(3),res.getString(4),res.getString(5),a1,res.getInt(7),res.getString(2));
            }
            return a; 
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }

    @Override
    public boolean delete(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet display(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet display(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet display(String pass, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
