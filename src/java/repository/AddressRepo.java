/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Address;
import model.Database;
import javax.persistence.Persistence;
/**
 *
 * @author Win10
 */
public class AddressRepo {
    public static Address getAddressById(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Address> query = em.createNamedQuery("Address.findById", Address.class);
                query.setParameter("id", id);
                Address result = query.getSingleResult();
                return result;
            } catch (Exception ex) {
                return null;
            }finally{
                em.close();
            }
        } catch (Exception e) {
            System.out.println("Error connecting to database");
            return null;
        }
    }
    public static Boolean createAddress(String zipCode, String city, String address, String addressComplement) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                Address new_address = new Address(zipCode, city, address, addressComplement);
                em.getTransaction().begin();
                em.persist(new_address);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                return false;
            }finally{
                em.close();
            }
        } catch (Exception e) {
            System.out.println("Error connecting to database");
            return false;
        }
    }

    public static Boolean updateAddress(Address address_object, String zipCode, String city, String address, String addressComplement) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                if(zipCode != null){
                    address_object.setZipCode(zipCode);
                }
                if(city != null){
                    address_object.setCity(city);
                }
                if(address != null){
                    address_object.setAddress(address);
                }
                if(addressComplement != null){
                    address_object.setAddressComplement(addressComplement);
                }
                
                em.merge(address_object);
                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                return false;
            }finally{
                em.close();
            }
        } catch (Exception e) {
            System.out.println("Error connecting to database");
            return false;
        }
    }

    public static List<Address> listAddresses() {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Address> query = em.createNamedQuery("Address.findAll", Address.class);
                List<Address> result = query.getResultList();
                return result;
            } catch (Exception ex) {
                return null;
            }finally{
                em.close();
            }
        } catch (Exception e) {
            System.out.println("Error connecting to database");
            return null;
        }
    }
    
}
