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
    ArrayList<City> heuristicCities = new ArrayList<>();
    public City parent;

    public City(String cityName, Info cityInfo) {
        this.cityName = cityName;
        this.cityInfo = cityInfo;
        adjacentCities = new ArrayList<>();
    }

    public City(String cityName) {
        this.cityName = cityName;
        adjacentCities = new ArrayList<>();
    }

    public static void setMainCities(ArrayList<City> mainCities) {
        City.mainCities = mainCities;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityInfo(Info cityInfo) {
        this.cityInfo = cityInfo;
    }

    public void setAdjacentCities(ArrayList<City> adjacentCities) {
        this.adjacentCities = adjacentCities;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    public static ArrayList<City> getMainCities() {
        return mainCities;
    }

    public String getCityName() {
        return cityName;
    }

    public Info getCityInfo() {
        return cityInfo;
    }

    public ArrayList<City> getAdjacentCities() {
        return adjacentCities;
    }

    public City getParent() {
        return parent;
    }

    public void addAdjacentCity(City cityAdded){
        adjacentCities.add(cityAdded);
    }
    public void addHeuristicCity(City cityAdded){
        heuristicCities.add(cityAdded);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityInfo=" + cityInfo +
                ", adjacentCities=" + adjacentCities +
                ", heuristicCities=" + heuristicCities +
                ", parent=" + parent +
                '}';
    }
}
