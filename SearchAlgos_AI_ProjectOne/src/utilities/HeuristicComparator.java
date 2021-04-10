package utilities;


import classes.HeuristicNode;

import java.util.Comparator;

public class HeuristicComparator implements Comparator<HeuristicNode> {
    @Override
    public int compare(HeuristicNode c1, HeuristicNode c2) {
        if (c1.getValue() > c2.getValue())
            return 1;
        else if (c1.getValue() < c2.getValue())
            return -1;
        else return 0;
    }
}
