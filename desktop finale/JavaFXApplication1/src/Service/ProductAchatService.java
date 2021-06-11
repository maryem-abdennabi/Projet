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
import Entity.ProductAchat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Ibrahim
 */
public class ProductAchatService implements IService<ProductAchat> {
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    private List<ProductAchat> productAchats;
    
    public ProductAchatService(){
        this.cnx = DataSource.getInstance().getCon();

    }

    @Override
    public boolean insert(ProductAchat t) {
String sql = "insert into prod_achat (product_id,achat_id,qte) values (?,?,?)";
        try {
            int a1 = (int) Math.round(t.getQuantite());

            pst = cnx.prepareCall(sql);
            pst.setInt(3,a1);
            pst.setInt(1, t.getProduct().getId_product());
            pst.setInt(2, t.getAchat().getId_achat());
            pst.executeUpdate();
            System.out.println("Achat added successfully");


            pst.executeUpdate();
            System.out.println("product achat added successfully");

            return true;
        } catch (SQLException ex) {
            System.out.println("product  achat pas ");
            System.out.println(ex.toString());

            return false;

        }     }

    @Override
    public boolean update(ProductAchat t) {
String sql = "update prod_achat set  qte= ?   where achat_id= ?";
        try {
     
            pst = cnx.prepareCall(sql);
            pst.setFloat(1, t.getQuantite());
            pst.setInt(2, t.getId_product_achat());

            pst.executeUpdate();
            System.out.println("product achat updated");
            return true;
        } catch (SQLException ex) {
            
            return false;
        }    }

    
    public boolean delete(int id) {
String sql="delete from prod_achat where id= ?";
				
		try {
                    pst=cnx.prepareStatement(sql);
                    pst.setInt(1, id);
                    pst.execute();
                    System.out.println("Product achat deleted ssssss");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			return false;
		}    }
    public boolean deleteByIdProduct(int id_product,int id_achat){
        String sql="delete from prod_achat where product_id= ? and achat_id= ? ";
				
		try {
                    pst=cnx.prepareStatement(sql);
                    pst.setInt(1, id_product);
                    pst.setInt(2, id_achat);

                    pst.execute();
                    System.out.println("Product achat deleted");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			return false;
		} 
    }

    @Override
    public List<ProductAchat> displayAll() {
        String sql="Select* from prod_achat";
       
        ProductService productService =new ProductService();
        AchatService achatService =new AchatService();
        
        List<ProductAchat> list=new ArrayList<>();
        Product product=null;
        Achat achat=null;
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            
            while(res.next()){
              //  product=productService.findById(res.getInt("id_product"));
              //  achat = achatService.findById(res.getInt("id_achat"));
                list.add(new ProductAchat(res.getInt("id"),res.getInt("qte"),productService.findById(res.getInt("product_id")),achatService.findById(res.getInt("achat_id"))));
            }
            return list;
        } catch (SQLException ex) {
             System.out.println(ex.toString());
        }
        return list; 
     }
    
     public List<ProductAchat> listProductAchats(){
         return productAchats.stream().distinct().collect(Collectors.toList());
         
     }
     public List<ProductAchat> displayProductAchat(int id){
           String sql="Select* from prod_achat where achat_id="+id;
       
       
   
        List<ProductAchat> list=new ArrayList<>();
        Product product=null;
                ProductService productService =new ProductService();
        AchatService achatService =new AchatService();
        Achat achat=null;
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            
            while(res.next()){
              //  product=productService.findById(res.getInt("id_product"));
              //  achat = achatService.findById(res.getInt("id_achat"));
                list.add(new ProductAchat(res.getInt("id"),res.getInt("qte"),productService.findById(res.getInt("product_id")),achatService.findById(res.getInt("achat_id"))));
            }
            return list;
        } catch (SQLException ex) {
             System.out.println(ex.toString());
        }
        return list; 
         
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
