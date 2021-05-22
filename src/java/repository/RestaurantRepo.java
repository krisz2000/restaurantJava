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
import model.Employee;
import model.Restaurant;

/**
 *
 * @author Win10
 */
public class RestaurantRepo {

    public static Boolean removeRestaurant(Restaurant restaurant) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                if(restaurant.getIsActive() == false){
                    return false;
                }
                em.getTransaction().begin();
                restaurant.setIsActive(false);
                em.merge(restaurant);
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

    public static List<Restaurant> listRestaurants() {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Restaurant> query = em.createNamedQuery("Restaurant.findAll", Restaurant.class);
                List<Restaurant> result = query.getResultList();
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

    public static Restaurant getRestaurantById(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Restaurant> query = em.createNamedQuery("Restaurant.findById", Restaurant.class);
                query.setParameter("id", id);
                Restaurant result = query.getSingleResult();
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

    public static Boolean createRestaurant(String name, Address address) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                Restaurant new_restaurant = new Restaurant(name, address);
                em.getTransaction().begin();
                em.persist(new_restaurant);
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

    public static Boolean updateRestaurant(Restaurant restaurant, String name, Address address) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                if(name != null){
                    restaurant.setName(name);
                }
                if(address != null){
                    restaurant.setAddressId(address);
                }
                em.merge(restaurant);
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

    public static Restaurant getRestaurantByName(String name) {
        try {
             EntityManager em = Database.getDbConn();
             try {
                 TypedQuery<Restaurant> query = em.createNamedQuery("Restaurant.findByName", Restaurant.class);
                 query.setParameter("name", name);
                 Restaurant result = query.getSingleResult();
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
