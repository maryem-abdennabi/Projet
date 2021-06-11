/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Ahmed
 */
import Connexion.DataSource;
import Entity.Priority;
import Entity.Product;
import Entity.Reclamation;
import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.getString;
import static jdk.nashorn.internal.objects.Global.instance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmed
 */
public class ReclamationService {
     private Connection cnx;
        private PreparedStatement pst;
            private static ReclamationService instance;

        private ResultSet rs;
        private Statement st;
        
        public ReclamationService(){
         cnx = DataSource.getInstance().getCon();

        } 
        public static ReclamationService getInstance()
    {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance; 
    }
         
         
         

    
    public boolean ajouteReclamation(Reclamation Reclamation) {

                    int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `reclamation`(`priority`,`idUser`,`username`,`user_mail`,`contenu`,`etat`,`date`,`idProduct`) VALUES (?,?,?,?,?,?,?,?)";
        pst =cnx.prepareStatement(req);
        pst.setInt(1, Reclamation.getPriority());
        pst.setInt(2,Reclamation.getId_user());
        pst.setString(3,Reclamation.getUsername());
        pst.setString(4,Reclamation.getMail());
        pst.setString(5,Reclamation.getContenu());
        pst.setString(6, Reclamation.getEtat());
        pst.setDate(7,(Date) Reclamation.getDate_rec());
        pst.setInt(8, Reclamation.getId_product());
        
        verf=pst.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
        
        
    }
    public int count() throws SQLException{
       try (Statement s = cnx.createStatement()) {
    final ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM reclamation");
    rs.next();
    int j=rs.getInt(1);
    return j;
  }
        
    }
    public List<Reclamation> affiche(int id) throws SQLDataException
    {

       List<Reclamation> list=new ArrayList<Reclamation>();
           try {
               String req="SELECT * FROM `reclamation` where `id`='"+id+"'";
               Statement st;
                 st = cnx.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                          Reclamation R = new Reclamation();
                R.setId_product(rs.getInt(9));
                R.setId_reclamation(rs.getInt(1));
                R.setPriority(rs.getInt(2));
                R.setId_user(rs.getInt(3));
                R.setUsername(rs.getString(4));
                R.setMail(rs.getString(5));
                R.setContenu(rs.getString(6));
                R.setDate_rec(rs.getDate(8));
                R.setEtat(rs.getString(7));
               
                           list.add(R);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;
    }

 public boolean  ModifierUser(Reclamation reclamation){
         int verf = 0 ;
        try{
        String req ;
        
        req="UPDATE reclamation set contenu=?,etat=?, where id=?";
        PreparedStatement res=cnx.prepareStatement(req);
        res.setInt(1, reclamation.getPriority());
        res.setInt(2, reclamation.getId_user());
        res.setString(3, reclamation.getUsername());
         res.setString(4, reclamation.getMail());
        res.setString(5, reclamation.getContenu());
        res.setString(6, reclamation.getEtat());
        res.setDate(7, reclamation.getDate_rec());
        res.setInt(8, reclamation.getId_product());
        
        verf=res.executeUpdate();
        }
        catch(SQLException e ){
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
 public boolean  ModifierUser2(Reclamation reclamation){
         int verf = 0 ;
        try{
        String req ;
        
        req="UPDATE reclamation set contenu=? where id=?";
        PreparedStatement res=cnx.prepareStatement(req);
        
        res.setInt(2, reclamation.getId_reclamation());
        res.setString(1, reclamation.getContenu());  
        verf=res.executeUpdate();
         }
        catch(SQLException e ){
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
    
    public List<Reclamation> DisplayAll(){
        
        List<Reclamation> list = new ArrayList<Reclamation>();
        int count =0;
        
        String req="select * from reclamation";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
                
                Reclamation R = new Reclamation();
                R.setId_product(rs.getInt(9));
                R.setId_reclamation(rs.getInt(1));
                R.setUsername(rs.getString(4));
                R.setContenu(rs.getString(6));
                R.setDate_rec(rs.getDate(8));
                R.setEtat(rs.getString(7));


                list.add(R);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
} 
    
    public List<String> DisplayP()
    {
       String sql="Select niveau from priority";
        List<String> list=new ArrayList<>();
        try {
            st=cnx.createStatement();
           rs=st.executeQuery(sql);
            while(rs.next()){
                list.add(rs.getString("niveau"));
            }
            return list;
        } catch (SQLException ex) {
                        Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list; 

    }
    public int idP(String niv) throws SQLException
    {
        try (Statement s = cnx.createStatement()) {
    final ResultSet rs = s.executeQuery("SELECT id FROM priority where niveau='"+niv+"'");
    rs.next();
    int j=rs.getInt(1);
    return j;
    }
    }
    public List<Reclamation> DisplayAlll(){
        
        List<Reclamation> list = new ArrayList<Reclamation>();
        int count =0;
        
        String req="select user_mail,priority,username,idProduct,contenu,date,etat from reclamation ";
         try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
                
                Reclamation R = new Reclamation();
                R.setId_product(rs.getInt(4));
                R.setContenu(rs.getString(5));
                R.setDate_rec(rs.getDate(6));
                R.setUsername(rs.getString(3));
                R.setMail(rs.getString(1));
                R.setPriority(rs.getInt(2));
                R.setEtat(rs.getString(7));
                list.add(R);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
} 
    
    
    
    
   public boolean UpdateRec (Reclamation reclamation ){
          
      
        try{
            PreparedStatement pst = cnx.prepareStatement("update reclamation set etat = 'Traiter' where id=?"); 
            pst.setInt(1,reclamation.getId_reclamation());
            pst.executeUpdate();
            return true ;

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return false ;
        }
       
          
          
          
    }
   public boolean SupprimerReclamation(int a) 
   {
         try {
             String req= "DELETE FROM reclamation WHERE id="+a;
             st=cnx.createStatement();
            st.executeUpdate(req);
             System.out.println("supprimer avec succee");
             return true ;
         } catch (SQLException ex) {
             Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                          return false;

         }
      
    }
 }

   
   

    
  

