package utilities;

import classes.City;

import java.util.Comparator;

public class CityComparator implements Comparator<City> {
    public int compare(City c1, City c2){
        if (c1.getCityInfo().getSummation() > c2.getCityInfo().getSummation())
            return 1;
        else if (c1.getCityInfo().getSummation() < c2.getCityInfo().getSummation())
            return -1;
        else return 0;
    }

}
