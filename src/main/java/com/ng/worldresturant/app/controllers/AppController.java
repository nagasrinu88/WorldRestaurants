/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.controllers;

import com.ng.worldresturant.app.services.RestaurantService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Owner
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(path = "/cuisines", method = RequestMethod.GET)
    public ResponseEntity getUniqueCuisines() {
        Set<String> cuisines = restaurantService.getUniqueCuisines();
        return ResponseEntity.ok(cuisines);
    }

    @RequestMapping(path = "/restaurants", method = RequestMethod.GET)
    public ResponseEntity getUniqueCuisines(@RequestParam String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit) {
        Page restaurants = restaurantService.matchByName(name, PageRequest.of(page, limit));
        return ResponseEntity.ok(restaurants);
    }
}
