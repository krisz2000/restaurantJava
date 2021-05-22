/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import model.Address;
import model.Employee;
import model.Restaurant;
import repository.EmployeeRepo;

/**
 *
 * @author Win10
 */
public class EmployeeService {

    public static Employee getEmployeeById(Integer id) {
        return EmployeeRepo.getEmployeeById(id);
    }

    public static List<Employee> listEmployees() {
        return EmployeeRepo.listEmployees();
    }

    public static Boolean createEmployee(String firstName, String lastName, Date birthDate, Integer addressId, Integer bossId, Integer restaurantId) {
        Restaurant restaurant = RestaurantService.getRestaurantById(restaurantId);
        if(restaurant == null){
            return false;
        }
        Employee boss = null;
        if(bossId != null){
            boss = EmployeeService.getEmployeeById(bossId);
            if(boss == null){
                return false;
            }
        }
        Address address = null;
        if(addressId != null){
            address = AddressService.getAddressById(addressId);
            if(address == null){
                return false;
            }
        }
        return EmployeeRepo.createEmployee(firstName, lastName, birthDate, address, boss, restaurant);
    }

    public static Boolean removeEmployee(Integer id) {
        Employee employee = EmployeeRepo.getEmployeeById(id);
        if(employee == null){
            return false;
        }
        return EmployeeRepo.removeEmployee(employee);
    }

    public static Boolean updateEmployee(Integer id, String firstName, String lastName, Date birthDate, Integer addressId, Integer bossId, Integer restaurantId) {
        Employee employee = EmployeeRepo.getEmployeeById(id);
        if(employee == null){
            return false;
        }
        Restaurant restaurant = null;
        if(restaurantId != null){
            restaurant = RestaurantService.getRestaurantById(restaurantId);
            if(restaurant == null){
                return false;
            }
        }
        Employee boss = null;
        if(bossId != null){
            boss = EmployeeService.getEmployeeById(bossId);
            if(boss == null){
                return false;
            }
        }
        Address address = null;
        if(addressId != null){
            address = AddressService.getAddressById(addressId);
            if(address == null){
                return false;
            }
        }
        return EmployeeRepo.updateEmployee(employee, firstName, lastName, birthDate, address, boss, restaurant);
    }

    public static Employee getEmployeeByFirstName(String firstName) {
        return EmployeeRepo.getEmployeeByFirstName(firstName);
    }
    
}
