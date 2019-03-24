/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.controllers;

import com.ng.worldresturant.app.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Owner
 */
@RestController
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(path = "/setup", method = RequestMethod.GET)
    public void setup() {
        restaurantService.setupReaustarunts();
    }

}
