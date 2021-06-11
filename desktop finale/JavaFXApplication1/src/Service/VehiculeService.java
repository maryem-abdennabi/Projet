package Service;

import Entity.Vehicule;
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
public class VehiculeService implements IService_T<Vehicule>{
    private Connection cnx;
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet ls;
    
    public VehiculeService() throws SQLException{
        cnx=DataSource.getInstance().getCon();
    }

    @Override
    public void insert(Vehicule v) {
        String req="insert into vehicule (matricule,weight,etat,marque,description) values (?,?,?,?,?)";

        try {
            pst=cnx.prepareStatement(req);
            
            pst.setString(1, v.getMatricule());
            pst.setFloat(2, v.getWeight());
            pst.setString(3, v.getEtat());
            pst.setString(4, v.getMarque());
            pst.setString(5, v.getDescription());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Vehicule p) {
     try {
            st = cnx.createStatement();
            st.executeUpdate("update vehicule set matricule='"+p.getMatricule()+"' ,weight="+p.getWeight()+",etat='"+p.getEtat()+"',marque='"+p.getMarque()+"',description='"+p.getDescription()+"' where id_vehicule="+p.getId_vehicule()+" ;");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;    
    }

    @Override
    public boolean delete(int id) {
int result = 0;
        try {
        pst = cnx.prepareStatement("delete from vehicule where id_vehicule=(?);");
        pst.setInt(1, id);
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

    @Override
    public List<Vehicule> displayAll() {
        String req="select * from vehicule";
        List<Vehicule> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Vehicule(ls.getInt(1),ls.getString(2),ls.getFloat(3),ls.getString(4),ls.getString(5),ls.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Vehicule> TrieMatricule() {
        String req="select * from vehicule order by matricule asc";
        List<Vehicule> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Vehicule(ls.getInt(1),ls.getString(2),ls.getFloat(3),ls.getString(4),ls.getString(5),ls.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Vehicule> TrieMarque() {
        String req="select * from vehicule order by marque";
        List<Vehicule> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Vehicule(ls.getInt(1),ls.getString(2),ls.getFloat(3),ls.getString(4),ls.getString(5),ls.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Vehicule> RechercheMarque(String marque) {
        String req="select * from vehicule where marque='"+marque+"'";
        List<Vehicule> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Vehicule(ls.getInt(1),ls.getString(2),ls.getFloat(3),ls.getString(4),ls.getString(5),ls.getString(6)));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<Vehicule> RechercheParLettreMarque(String ch) {
        String req="select * from vehicule where marque like '"+ch+"%'";
        List<Vehicule> list=new ArrayList<>();
        
        
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Vehicule(ls.getInt(1),ls.getString(2),ls.getFloat(3),ls.getString(4),ls.getString(5),ls.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     public boolean deleteAll() {
        int result = 0;
        try {
        pst = cnx.prepareStatement("delete from vehicule;");
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
    
    
}