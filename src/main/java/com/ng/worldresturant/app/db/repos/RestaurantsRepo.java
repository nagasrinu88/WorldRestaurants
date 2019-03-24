/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.db.repos;

import com.ng.worldresturant.app.db.Restaurants;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Owner
 */
@Repository
public interface RestaurantsRepo
        extends PagingAndSortingRepository<Restaurants, Long> {

    @Query(value = "select DISTINCT cuisines from restaurants_cuisines order by cuisines",
            nativeQuery = true)
    public Set<String> findUniqueCuisines();

    @Query(value = "SELECT v FROM Restaurants v WHERE v.name like %:name%",
            countQuery = "SELECT count(v) FROM Restaurants v WHERE v.name like %:name%")
    public Page<Restaurants> matchByName(@Param("name") String name,
            Pageable pageable);
}
