/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Address;
import repository.AddressRepo;

/**
 *
 * @author Win10
 */
public class AddressService {
    public static Address getAddressById(Integer id) {
        return AddressRepo.getAddressById(id);
    }
    public static Boolean createAddress(String zipCode, String city, String address, String addressComplement){
        return AddressRepo.createAddress(zipCode, city, address, addressComplement);
    }

    public static Boolean updateAddress(Integer id, String zipCode, String city, String address, String addressComplement) {
        Address address_object = AddressService.getAddressById(id);
        if(address_object == null){
            return false;
        }
        return AddressRepo.updateAddress(address_object, zipCode, city, address, addressComplement);
    }

    public static List<Address> listAddresses() {
        return AddressRepo.listAddresses();
    }

}
