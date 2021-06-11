/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Ibrahim
 */
public class ProductAchat {
    private int id_product_achat;
    private int qte;
    private Product product;
    private Achat achat;

    public ProductAchat(int id_product_achat, int quantite,Product product, Achat achat) {
        this.id_product_achat = id_product_achat;
        this.qte = quantite;
       
        this.product = product;
        this.achat = achat;
    }

    public ProductAchat(int quantite, Product product) {
        this.qte = quantite;
        this.product = product;
    }

    public ProductAchat(int quantite, Product product, Achat achat) {
        this.qte = quantite;      
        this.product = product;
        this.achat= achat;
    }

    public ProductAchat(int id_product_achat, int quantite) {
        this.id_product_achat = id_product_achat;
        this.qte = quantite;
    }
    
    public int getId_product_achat() {
        return id_product_achat;
    }

    public void setId_product_achat(int id_product_achat) {
        this.id_product_achat = id_product_achat;
    }

    public float getQuantite() {
        return qte;
    }

    public void setQuantite(int quantite) {
        this.qte = quantite;
    }

   

   

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Achat getAchat() {
        return achat;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }

    @Override
    public String toString() {
        return "Product "+product.getProduct_name()+" "+qte;
    }

   

    
}
