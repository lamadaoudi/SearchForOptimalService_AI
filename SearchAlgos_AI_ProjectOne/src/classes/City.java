/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author Main
 */
public class City {
    public static ArrayList<City> mainCities = new ArrayList<>();
    String cityName;
    Info cityInfo;
    ArrayList<City> adjacentCities;

    public City(String cityName, Info cityInfo) {
        this.cityName = cityName;
        this.cityInfo = cityInfo;
        adjacentCities = new ArrayList<>();
    }

    public City(String cityName) {
        this.cityName = cityName;
        adjacentCities = new ArrayList<>();
    }
    
    
    public void addAdjacentCity(City cityAdded){
        adjacentCities.add(cityAdded);
    }

    @Override
    public String toString() {
        return "City{" + "cityName=" + cityName + ", cityInfo=" + cityInfo + ", adjacentCities=" + adjacentCities + '}';
    }
    
    
    
    
}
