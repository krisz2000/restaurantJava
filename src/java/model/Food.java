/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f WHERE f.isActive=true")
    , @NamedQuery(name = "Food.findById", query = "SELECT f FROM Food f WHERE f.id = :id")
    , @NamedQuery(name = "Food.findByName", query = "SELECT f FROM Food f WHERE f.name = :name")
    , @NamedQuery(name = "Food.findByDescription", query = "SELECT f FROM Food f WHERE f.description = :description")
    , @NamedQuery(name = "Food.findByPicture", query = "SELECT f FROM Food f WHERE f.picture = :picture")
    , @NamedQuery(name = "Food.findByIsActive", query = "SELECT f FROM Food f WHERE f.isActive = :isActive")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 2147483647)
    @Column(name = "picture")
    private String picture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private boolean isActive;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurantId;

    public Food() {
    }

    public Food(Integer id) {
        this.id = id;
    }

    public Food(Integer id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public Food(String name, String description, String picture, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.restaurantId = restaurant;
        this.isActive = true;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public String serialize(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(this);
            return json;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Food[ id=" + id + " ]";
    }
    
}
