package utilities;

import classes.AStarStruct;
import java.util.Comparator;

public class AStarComparator implements Comparator<AStarStruct> {
    public int compare(AStarStruct c1, AStarStruct c2){
        if (c1.getCost() > c2.getCost())
            return 1;
        else if (c1.getCost()< c2.getCost())
            return -1;
        else return 0;
    }
}
