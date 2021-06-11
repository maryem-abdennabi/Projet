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
public class Stocks {
    private int id_stocks;	
    private float quantity;	
    private Product  id_product;
    private int id_entrepot;
    private String unity;

    public Stocks(int id_stocks, float quantity, Product id_product, String unity) {
        this.id_stocks = id_stocks;
        this.quantity = quantity;
        this.id_product = id_product;
        this.unity = unity;
    }

    public Stocks(float quantity, Product id_product,int id_entrepot ,String unity) {
        this.quantity = quantity;
        this.id_product = id_product;
        this.id_entrepot=id_entrepot;
        this.unity = unity;
    }

    @Override
    public String toString() {
        return "Stocks{" + "id_stocks=" + id_stocks + ", quantity=" + quantity + ", id_product=" + id_product + ", id_entrepot=" + id_entrepot + ", unity=" + unity + '}';
    }

    public int getId_stocks() {
        return id_stocks;
    }

    public float getQuantity() {
        return quantity;
    }

    public Product getId_product() {
        return id_product;
    }

    public void setId_stocks(int id_stocks) {
        this.id_stocks = id_stocks;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setId_product(Product id_product) {
        this.id_product = id_product;
    }

    public void setId_entrepot(int id_entrepot) {
        this.id_entrepot = id_entrepot;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
    

    public int getId_entrepot() {
        return id_entrepot;
    }

    public String getUnity() {
        return unity;
    }
    
    
    
}

