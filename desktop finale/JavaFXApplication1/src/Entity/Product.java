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
public class Product {
    private int id_product;
    private String product_name;
    private String product_type;	
    private String reference;	
    private String marque;	
    private float priceHT;	
    private float priceTTC;	
    private float TVA;	
    private float weight;

    public Product(String product_name, String product_type, String reference, String marque, float priceHT, float TVA, float weight) {
        this.product_name = product_name;
        this.product_type = product_type;
        this.reference = reference;
        this.marque = marque;
        this.priceHT = priceHT;
        this.TVA = TVA;
        this.weight = weight;
        this.priceTTC=this.priceHT*this.TVA;
    }

    public Product() {
    }

    public Product(int id_product, String product_name, String product_type, String reference, String marque, float priceHT, float priceTTC, float TVA, float weight) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.product_type = product_type;
        this.reference = reference;
        this.marque = marque;
        this.priceHT = priceHT;
        this.priceTTC = priceTTC;
        this.TVA = TVA;
        this.weight = weight;
    }
  
    public int getId_product() {
        return id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getReference() {
        return reference;
    }

    public String getMarque() {
        return marque;
    }

    public float getPriceHT() {
        return priceHT;
    }

    public float getPriceTTC() {
        return priceTTC;
    }

    public float getTVA() {
        return TVA;
    }

    public float getWeight() {
        return weight;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setPriceHT(float priceHT) {
        this.priceHT = priceHT;
    }

    public void setPriceTTC(float priceTTC) {
        this.priceTTC = priceTTC;
    }

    public void setTVA(float TVA) {
        this.TVA = TVA;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
     //   return "Product{" + "id_product=" + id_product + ", product_name=" + product_name + ", product_type=" + product_type + ", reference=" + reference + ", marque=" + marque + ", priceHT=" + priceHT + ", priceTTC=" + priceTTC + ", TVA=" + TVA + ", weight=" + weight + '}';
    
    return product_name;
    }
    
    
}
