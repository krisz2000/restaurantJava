/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
    static EntityManagerFactory emf = null;
    public static EntityManager getDbConn() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("RestaurantPU");
        }
        return emf.createEntityManager();
    }
}