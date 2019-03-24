/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.services;

import com.ng.worldresturant.app.db.Restaurants;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Owner
 */
public interface RestaurantService {

    void setupReaustarunts();

    Set<String> getUniqueCuisines();

    Page<Restaurants> matchByName(String name, Pageable pageable);
}
