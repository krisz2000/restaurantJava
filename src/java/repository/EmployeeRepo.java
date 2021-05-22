/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Address;
import model.Database;
import model.Employee;
import java.util.List;
import model.Restaurant;

/**
 *
 * @author Win10
 */
public class EmployeeRepo {

    public static Employee getEmployeeById(Integer id) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Employee> query = em.createNamedQuery("Employee.findById", Employee.class);
                query.setParameter("id", id);
                Employee result = query.getSingleResult();
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

    public static List<Employee> listEmployees() {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
                List<Employee> result = query.getResultList();
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

    public static Boolean createEmployee(String firstName, String lastName, Date birthDate, Address address, Employee boss, Restaurant restaurant) {
       try {
            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                Employee new_employee = new Employee(firstName, lastName, birthDate, address, boss, restaurant);
                em.persist(new_employee);
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

    public static Boolean removeEmployee(Employee employee) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                if(employee.getIsActive() == false){
                    return false;
                }
                em.getTransaction().begin();
                employee.setIsActive(false);
                em.merge(employee);
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

    public static Boolean updateEmployee(Employee employee, String firstName, String lastName, Date birthDate, Address address, Employee boss, Restaurant restaurant) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                if(firstName != null){
                    employee.setFirstName(firstName);
                }
                if(lastName != null){
                    employee.setLastName(lastName);
                }
                if(firstName != null){
                    employee.setFirstName(firstName);
                }
                if(birthDate != null){
                    employee.setBirthDate(birthDate);
                }
                if(address != null){
                    employee.setAddressId(address);
                }
                if(boss != null){
                    employee.setBossId(boss);
                }
                if(restaurant != null){
                    employee.setRestaurantId(restaurant);
                }
                
                em.merge(employee);
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

    public static Employee getEmployeeByFirstName(String firstName) {
        try {
            EntityManager em = Database.getDbConn();
            try {
                TypedQuery<Employee> query = em.createNamedQuery("Employee.findByFirstName", Employee.class);
                query.setParameter("firstName", firstName);
                Employee result = query.getSingleResult();
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
