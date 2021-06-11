package Service;

import Connexion.DataSource;
import Entity.Entrepot;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ibrahim
 */
public class EntrepotService implements IService<Entrepot> {

    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    ObservableList<Entrepot> oblist = FXCollections.observableArrayList();


    public EntrepotService() {
        this.cnx = DataSource.getInstance().getCon();
    }

    @Override
    public boolean insert(Entrepot t) {
        String sql = "insert into entrepot (address,nbrRangs,phone,phone_bis) values (?,?,?,?)";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, t.getAddress());
            pst.setInt(2, t.getNbrRangs());
            pst.setString(3, t.getPhone());
            pst.setString(4, t.getPhone_bis());
            pst.executeUpdate();
            System.out.println("warehouse added successfully");

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean update(Entrepot t) {
        String sql = "update entrepot set address= ? , nbrRangs= ? , phone= ? , phone_bis = ? where id_entrepot=?";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, t.getAddress());
            pst.setInt(2, t.getNbrRangs());
            pst.setString(3, t.getPhone());
            pst.setString(4, t.getPhone_bis());
            pst.setInt(5, t.getId_entrepot());
            pst.executeUpdate();
            System.out.println("entrepot updated");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean delete(int id) {
        String sql = "delete from entrepot where id_entrepot= ? ";

        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            System.out.println("entrepot deleted");
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public List<Entrepot> displayAll() {
        String sql = "Select * from entrepot";
        List<Entrepot> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()) {
                list.add(new Entrepot(res.getInt("id"), res.getString("address"), res.getInt("nbrRangs"), res.getString("phone"), res.getString("phone_bis"), res.getString("latitude"), res.getString("longitude")));
            }
          return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;
    }
        public Entrepot findById(int id) {
        String sql = "Select* from entrepot where id='" + id + "'";
        Entrepot e = null;
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);
            if (res.next()) {
                e= new Entrepot(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7));
            }
            return e;

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
         public ObservableList<Entrepot> findByName(String name) {
        String sql = "Select* from entrepot where address='" + name + "'";
        List<Entrepot> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(sql);
            if (res.next()) {
                oblist.add( new Entrepot(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7)));
            }
            return oblist;

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