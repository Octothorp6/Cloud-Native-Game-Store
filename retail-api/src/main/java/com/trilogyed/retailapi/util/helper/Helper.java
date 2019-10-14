package com.trilogyed.retailapi.util.helper;

/**
 * HELPER-CLASS
 */


public class Helper {
    // must check how many times the total is evenly divided by 50 and return a total point value.
    // 10 points for each $50 spent, not prorated. ($49 = 0, 110 == 20, and so on..)
    public static int calculatePointTotal(int total) {
        int minimum = 50;
        int result;
        int pointTotal;
        // if less than 50, return 0 points
        if (total < minimum) {
            return 0;
        }
        // otherwise, return the point total value
        result = total / minimum;
        pointTotal = result * 10;

        return pointTotal;
    }


}
