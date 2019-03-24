/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.db;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "ns_restaurants")
@Getter
@Setter
public class Restaurants implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ns_restaurants_seq")
    @SequenceGenerator(sequenceName = "ns_restaurants_seq",
            name = "ns_restaurants_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(nullable = false, unique = true)
    private String restaurantId;
    @Column(nullable = false)
    private String name;
    @ElementCollection    
    private List<String> cuisines;
    private float avgCostPerTwo;
    @Column(nullable = false)
    private String currency;
    private boolean hasTableBooking;
    private boolean hasOnlineDelivery;
    private float avgRating;
    private String ratingColor;
    private String ratingText;
    private int votes;
    @Embedded
    private Address address;
}
