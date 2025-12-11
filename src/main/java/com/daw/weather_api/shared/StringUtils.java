package com.daw.weather_api.shared;

public class StringUtils {

    public static String replaceAll(String aString, String[] toBeReplaced, String[] newValues) {
        if (toBeReplaced.length != newValues.length) {
            return aString;
        }

        int numToBeReplaced = toBeReplaced.length;

        for (int i = 0; i < numToBeReplaced; i++) {
            String oldValue = toBeReplaced[i];
            String newValue = newValues[i];
            aString = aString.replace(oldValue, newValue);
        }

        return aString;
    }

    public static String replaceAll(String aString, String[] toBeReplaced, double[] newValues) {
        if (toBeReplaced.length != newValues.length) {
            return aString;
        }

        int numToBeReplaced = toBeReplaced.length;

        for (int i = 0; i < numToBeReplaced; i++) {
            String oldValue = toBeReplaced[i];
            double newValue = newValues[i];
            aString = aString.replace(oldValue, String.valueOf(newValue));
        }

        return aString;
    }
}
