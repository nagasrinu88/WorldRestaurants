/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.services;

import com.ng.worldresturant.app.db.Address;
import com.ng.worldresturant.app.db.Restaurants;
import com.ng.worldresturant.app.db.repos.RestaurantsRepo;
import com.ng.worldresturant.utils.CsvReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Owner
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    static final String RESTAURANTS_FILE_PATH = "restaurants.csv";
    static final String LOCATIONS_FILE_PATH = "locations.csv";

    @Autowired
    private RestaurantsRepo restaurantsRepo;

    @Override
    @Transactional
    public void setupReaustarunts() {
        List<Map> restaurantData = CsvReader.loadObjectList(Map.class, RESTAURANTS_FILE_PATH);
        List<Map> locationData = CsvReader.loadObjectList(Map.class, LOCATIONS_FILE_PATH);
        int len = restaurantData.size();
        for (int i = 0; i < len; i++) {
            Map restaurantMap = restaurantData.get(i);
            Map locationMap = locationData.get(i);

            String restaurantName = String.valueOf(restaurantMap.get("Restaurant Name"));
            System.out.println("Adding Restaurant with name " + restaurantName + " -- START");
            Restaurants restaurant = new Restaurants();
            restaurant.setRestaurantId(String.valueOf(restaurantMap.get("Restaurant ID")));
            restaurant.setName(restaurantName);
            restaurant.setCuisines(Arrays.asList(String.valueOf(restaurantMap.get("Cuisines")).split(","))
                    .stream().map(String::trim).collect(Collectors.toList())
            );
            restaurant.setAvgCostPerTwo(Float.valueOf((String) restaurantMap.get("Average Cost for two")));
            restaurant.setCurrency(String.valueOf(restaurantMap.get("Currency")));
            restaurant.setHasTableBooking(String.valueOf(restaurantMap.get("Has Table booking")).equalsIgnoreCase("YES"));
            restaurant.setHasOnlineDelivery(String.valueOf(restaurantMap.get("Has Online delivery")).equalsIgnoreCase("YES"));
            restaurant.setAvgRating(Float.valueOf((String) restaurantMap.get("Aggregate rating")));
            restaurant.setRatingColor(String.valueOf(restaurantMap.get("Rating color")));
            restaurant.setRatingText(String.valueOf(restaurantMap.get("Rating text")));
            restaurant.setVotes(Integer.valueOf((String) restaurantMap.get("Votes")));

            //Preparing address object
            Address address = new Address();
            address.setCountryCode(Integer.valueOf((String) locationMap.get("Country Code")));
            address.setCity(String.valueOf(locationMap.get("City")));
            address.setAddress(String.valueOf(locationMap.get("Address")));
            address.setLocality(String.valueOf(locationMap.get("Locality")));
            address.setLongitude(Double.valueOf((String) locationMap.get("Longitude")).floatValue());
            address.setLatitude(Double.valueOf((String) locationMap.get("Latitude")).floatValue());

            restaurant.setAddress(address);
            restaurantsRepo.save(restaurant);
            System.out.println("Adding Restaurant with name " + restaurantName + " -- COMPLETED");
        }
    }

    @Override
    public Set<String> getUniqueCuisines() {
        return restaurantsRepo.findUniqueCuisines();
    }

    @Override
    public Page<Restaurants> matchByName(String name, Pageable pageable) {
        return restaurantsRepo.matchByName(name, pageable);
    }
}
