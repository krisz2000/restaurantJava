/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Address;
import model.Restaurant;
import repository.RestaurantRepo;

/**
 *
 * @author Win10
 */
public class RestaurantService {

    public static Boolean removeRestaurant(Integer id) {
        Restaurant restaurant = getRestaurantById(id);
        if(restaurant == null){
            return false;
        }
        return RestaurantRepo.removeRestaurant(restaurant);
    }

    public static List<Restaurant> listRestaurants() {
        return RestaurantRepo.listRestaurants();
    }

    public static Restaurant getRestaurantById(Integer id) {
        return RestaurantRepo.getRestaurantById(id);
    }

    public static Boolean createRestaurant(String name, Integer addressId) {
        Address address = AddressService.getAddressById(addressId);
        if(address == null){
            return false;
        }
        return RestaurantRepo.createRestaurant(name, address);
    }

    public static Boolean updateRestaurant(Integer id, String name, Integer addressId) {
        Restaurant restaurant = getRestaurantById(id);
        if(restaurant == null){
            return false;
        }
        Address address = null;
        if(addressId != null){
            address = AddressService.getAddressById(addressId);
            if(address == null){
                return false;
            }
        }
        
        return RestaurantRepo.updateRestaurant(restaurant, name, address);
    }

    public static Restaurant getRestaurantByName(String name) {
        return RestaurantRepo.getRestaurantByName(name);
    }
    
}
