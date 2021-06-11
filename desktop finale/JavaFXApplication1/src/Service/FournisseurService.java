/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Connexion.DataSource;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import Entity.Entrepot;
import Entity.Fournisseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author Ibrahim
 */
public class FournisseurService implements IService<Fournisseur>,IServiceSearch<Fournisseur> {

    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    private List<Fournisseur> fournisseurs;
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    public FournisseurService() {
        this.cnx = DataSource.getInstance().getCon();
        fournisseurs=new ArrayList<>();
        String sql="Select* from fournisseur";
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                fournisseurs.add(new Fournisseur(res.getInt("id"),res.getString("firstname"),res.getString("lastname"),res.getString("phoneNumber"),res.getString("address"),res.getString("email")));
            }
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean insert(Fournisseur t) {
        String sql = "insert into fournisseur (firstname,lastname,phoneNumber,address,email) values (?,?,?,?,?)";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, t.getFirstname());
            pst.setString(2, t.getLastname());
            pst.setString(3, t.getPhoneNumber());
            pst.setString(4, t.getAddress());
            pst.setString(5, t.getEmail());

            pst.executeUpdate();
            System.out.println("Fournisseur added successfully");

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean update(Fournisseur t) {
        String sql = "update fournisseur set firstname= ? , lastname= ? , phoneNumber= ?  , address= ? , email= ? where id= ?  ";
        try {
            pst = cnx.prepareCall(sql);
            pst.setString(1, t.getFirstname());
            pst.setString(2, t.getLastname());
            pst.setString(3, t.getPhoneNumber());
            pst.setString(4, t.getAddress());
            pst.setString(5, t.getEmail());
            pst.setInt(6, t.getId_fournisseur());

            pst.executeUpdate();
            System.out.println("fournisseur updated");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int id) {
String sql="delete from fournisseur where id= ? ";
				
		try {
                    pst=cnx.prepareStatement(sql);
                    pst.setInt(1, id);
                    pst.execute();
                    System.out.println("fournisseur deleted");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			return false;
		}     }

    @Override
    public List<Fournisseur> displayAll() {
String sql="Select* from fournisseur";
        List<Fournisseur> list=new ArrayList<>();
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                list.add(new Fournisseur(res.getInt("id"),res.getString("firstname"),res.getString("lastname"),res.getString("phoneNumber"),res.getString("address"),res.getString("email")));
            }
            return list;
        } catch (SQLException ex) {
                        Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;    }
    public void download(Fournisseur f,String file) {
        Document document = new Document(PageSize.A4);
        document.addAuthor("brahim");
        document.addTitle("Facture");
        
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file+".pdf"));
            document.open();
            generateQRCodeImage( 150, 150, QR_CODE_IMAGE_PATH,f);
            System.out.println("aaaa");
            Paragraph para = new Paragraph(f.toString());
            Image img = Image.getInstance(QR_CODE_IMAGE_PATH);

            document.add(para);
            document.add(para);
            document.add(para);
            document.add(para);
            document.add(para);
            document.add(para);
            document.add(para);
            document.add(img);
            document.close();
            System.out.println("Document generated");

        } catch (DocumentException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FournisseurService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(FournisseurService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FournisseurService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    private static void generateQRCodeImage(int width, int height, String filePath,Fournisseur f)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(f.getEmail(), BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public List<Fournisseur> getAll() {
        return this.fournisseurs;
    }

    public List<Fournisseur> findByName(String name) {
        return fournisseurs.stream().filter(x->x.getFirstname().equals(name)).collect(Collectors.toList());
    }

    public Fournisseur findFirstByName(String name) {
        return fournisseurs.stream().filter(x->x.getFirstname().equals(name)).findFirst().get();
    }

    @Override
    public Fournisseur findByPhoneNumber(String phone) {
        return fournisseurs.stream().filter(x->x.getPhoneNumber().equals(phone)).findFirst().get();
    }

    @Override
    public Fournisseur findByEmail(String mail) {
        return fournisseurs.stream().filter(x->x.getEmail().equals(mail)).findFirst().get();
    }

    @Override
    public List<Fournisseur> findAllByFirstName(String name) {
        return fournisseurs.stream().filter(x->x.getFirstname().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Fournisseur> findAllByLastName(String name) {
        return fournisseurs.stream().filter(x->x.getLastname().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Fournisseur> findByAddress(String address) {
        return fournisseurs.stream().filter(x->x.getAddress().equals(address)).collect(Collectors.toList());
    }

    @Override
    public List<Fournisseur> findFournisseursByNames(String name) {

        return fournisseurs.stream().filter(x->x.getFirstname().matches(name)).collect(Collectors.toList());
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
