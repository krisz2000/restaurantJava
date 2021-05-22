/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Database;
import model.Food;
import model.Restaurant;

/**
 *
 * @author Win10
 */
public class FoodRepo {

    public static Food getFoodById(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Food> query = em.createNamedQuery("Food.findById", Food.class);
                query.setParameter("id", id);
                Food result = query.getSingleResult();
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

    public static Boolean removeFood(Food food) {
        try{
            EntityManager em = Database.getDbConn();
            try {
                if(food.getIsActive() == false){
                    return false;
                }
                em.getTransaction().begin();
                food.setIsActive(false);
                em.merge(food);
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

    public static List<Food> listFoods() {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Food> query = em.createNamedQuery("Food.findAll", Food.class);
                List<Food> result = query.getResultList();
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

    public static Boolean createFood(String name, String description, String picture, Restaurant restaurant) {
        try {
            
            EntityManager em = Database.getDbConn();
            try {
                Food new_food = new Food(name, description, picture, restaurant);
                em.getTransaction().begin();
                em.persist(new_food);
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

    public static Boolean updateFood(Food food, String name, String description, String picture, Restaurant restaurant) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                if(name != null){
                    food.setName(name);
                }
                if(description != null){
                    food.setDescription(description);
                }
                if(picture != null){
                    food.setPicture(picture);
                }
                if(restaurant != null){
                    food.setRestaurantId(restaurant);
                }
                em.merge(food);
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

    public static Food getFoodByName(String name) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Food> query = em.createNamedQuery("Food.findByName", Food.class);
                query.setParameter("name", name);
                Food result = query.getSingleResult();
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
