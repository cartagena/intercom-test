package com.github.cartagena.intercom.array;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;

public class ArrayOperations {

    /**
     * Flats an array of arbitrary nested arrays of {@link Integer}.
     *
     * @param array an array of {@link Object} containing only integers or nested array(s) of integers.
     * @return a flattened array of {@link Integer}
     * @throws IllegalAccessException if the input array if invalid, ie, is null or contains elements other than
     * integers and nested array(s) of integers.
     */
    public Integer[] flatIntegerArray(Object[] array) {
        if(isNull(array)) {
            throw new IllegalArgumentException("Argument should not be null.");
        }

        ArrayList<Integer> flattenedList = new ArrayList<>();

        for(Object element : array) {
            if(!isValidElement(element)) {
                throw new IllegalArgumentException("Argument should contain only integers or array of integers.");
            }

            if(isNestedArray(element)) {
                Integer[] n = flatIntegerArray((Object[]) element);
                flattenedList.addAll(asList(n));
            } else {
                flattenedList.add((Integer) element);
            }
        }

        return flattenedList.toArray(new Integer[flattenedList.size()]);
    }

    private boolean isValidElement(Object element) {
        return element instanceof Integer || isNestedArray(element);
    }

    private boolean isNestedArray(Object element) {
        return element instanceof Object[];
    }

}
