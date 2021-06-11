/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Ibrahim
 */
public interface IServiceSearch<T> {
    public List<T> getAll();
    public List<T> findByName(String name);
    public T findFirstByName(String name);
    public T findByPhoneNumber(String phone);
    public T findByEmail(String mail);
    public List<T> findAllByFirstName(String name);
    public List<T> findAllByLastName(String name);
    public List<T> findByAddress(String address);
    public List<T> findFournisseursByNames(String name);
    
    
    
}
