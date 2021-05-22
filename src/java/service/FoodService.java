/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Food;
import model.Restaurant;
import repository.FoodRepo;

/**
 *
 * @author Win10
 */
public class FoodService {

    public static Boolean removeFood(Integer id) {
        Food food = getFoodById(id);
        if(food == null){
            return false;
        }
        return FoodRepo.removeFood(food);
    }

    public static List<Food> listFoods() {
        return FoodRepo.listFoods();
    }

    public static Food getFoodById(Integer id) {
        return FoodRepo.getFoodById(id);
    }

    public static Boolean createFood(String name, String description, String picture, Integer restaurantId) {
        Restaurant restaurant = RestaurantService.getRestaurantById(restaurantId);
        if(restaurant == null){
            return false;
        }
        return FoodRepo.createFood(name, description, picture, restaurant);
    }

    public static Boolean updateFood(Integer id, String name, String description, String picture, Integer restaurantId) {
        Food food = getFoodById(id);
        if(food == null){
            return false;
        }
        Restaurant restaurant = null;
        if(restaurantId != null){
            restaurant = RestaurantService.getRestaurantById(restaurantId);
            if(restaurant == null){
                return false;
            }
        }
        return FoodRepo.updateFood(food, name, description, picture, restaurant);
    }

    public static Food getFoodByName(String name) {
        return FoodRepo.getFoodByName(name);
    }
    
}
