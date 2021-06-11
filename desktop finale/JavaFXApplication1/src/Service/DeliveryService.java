/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Delivery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connexion.DataSource;

/**
 *
 * @author Firas
 */
public class DeliveryService implements IService_T<Delivery>{
    private Connection cnx;
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet ls;
    
    public DeliveryService() throws SQLException{
        cnx=DataSource.getInstance().getCon();
    }

    @Override
    public void insert(Delivery t) {
        String req="insert into delivery (reference,client_name,driver_name,address,statut,vehicule) values (?,?,?,?,?,?)";

        try {
            pst=cnx.prepareStatement(req);
            
            pst.setString(1, t.getReference());
            pst.setString(2, t.getClient_name());
            pst.setString(3, t.getDriver_name());
            pst.setString(4, t.getAddress());
            pst.setString(5, t.getStatut());
            pst.setInt(6, t.getVehicule());

            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Delivery t) {
        try {
            st = cnx.createStatement();
            st.executeUpdate("update delivery set reference= '"+t.getReference()+" ' ,client_name='"+t.getClient_name()+"', driver_name='"+t.getDriver_name()+"' , address='"+t.getAddress()+"' , statut='"+t.getStatut()+"' , vehicule="+t.getVehicule()+" where id_delivery="+t.getId_delivery()+";");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;       
    }

    @Override
    public boolean delete(int id) {
        int result = 0;
        try {
        pst = cnx.prepareStatement("delete from delivery where id_delivery=(?);");
        pst.setInt(1, id);
        result = pst.executeUpdate();

        } catch (SQLException ex) {
        Logger.getLogger(IService.class.getName()).log(Level.SEVERE, null,ex);
        }
        if (result == 1) {
        return true;
        }
        return false; 
    }

    
    @Override
    public List<Delivery> displayAll() {
        String req="select * from delivery";
        List<Delivery> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Delivery(ls.getInt(1),ls.getString(3),ls.getString(4),ls.getString(5),ls.getString(6),ls.getString(7),ls.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Delivery> TrieReference() {
        String req="select * from delivery order by reference";
        List<Delivery> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Delivery(ls.getInt(1),ls.getString(2),ls.getString(3),ls.getString(4),ls.getString(5),ls.getString(6),ls.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<Delivery> TrieClient() {
        String req="select * from delivery order by client_name";
        List<Delivery> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Delivery(ls.getInt(1),ls.getString(2),ls.getString(3),ls.getString(4),ls.getString(5),ls.getString(6),ls.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
     public List<Delivery> TrieDriver() {
        String req="select * from delivery order by driver_name";
        List<Delivery> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Delivery(ls.getInt(1),ls.getString(2),ls.getString(3),ls.getString(4),ls.getString(5),ls.getString(6),ls.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
      public boolean deleteAll() {
        int result = 0;
        try {
        pst = cnx.prepareStatement("delete from delivery;");
        result = pst.executeUpdate();

        } catch (SQLException ex) {
        Logger.getLogger(IService.class.getName()).log(Level.SEVERE, null,
       ex);
        }
        if (result == 1) {
        return true;
        }
        return false; 
    }
      
      public List<Delivery> RechercheClient(String client) {
        String req="select * from delivery where client_name='"+client+"'";
        List<Delivery> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Delivery(ls.getInt(1),ls.getString(2),ls.getString(3),ls.getString(4),ls.getString(5),ls.getString(6),ls.getInt(7)));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public List<Delivery> RechercheParLettreReference(String ch) {
        String req="select * from delivery where reference like '"+ch+"%'";
        List<Delivery> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Delivery(ls.getInt(1),ls.getString(2),ls.getString(3),ls.getString(4),ls.getString(5),ls.getString(6),ls.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

