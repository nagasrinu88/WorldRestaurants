/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ng.worldresturant.app.db;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Owner
 */
@Embeddable
@Getter
@Setter
public class Address implements Serializable {

    private int countryCode;
    private String city;
    private String address;
    private String locality;
    private float longitude;
    private float latitude;
}
