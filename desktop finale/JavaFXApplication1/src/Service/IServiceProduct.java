/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Product;
import java.util.List;

/**
 *
 * @author Ibrahim
 */
public interface IServiceProduct<T> {
    List<T> findByName(String name);
    Product findFirstOneByName(String name);
    Product findById(int id);
    Product findFirstOneByType(String type);
    List<Product> findByType(String type);
    Product findByReference(String reference);
    Product findFirstByMarque(String marque);
    List<Product> findByMarque(String marque);
    
    
    
}
