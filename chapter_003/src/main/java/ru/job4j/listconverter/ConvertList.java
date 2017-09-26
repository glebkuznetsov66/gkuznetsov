package ru.job4j.listconverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class
 *
 * @author gkuznetsov.
 * @version 0.1.
 * @since 26.09.2017.
 */
public class ConvertList {
    /**
     * Convert array to List.
     * @param array - original array.
     * @return List - list.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] outer : array) {
            for(int inner : outer) {
                list.add(inner);
            }
        }
        return list;
    }

    /**
     * Convert list to arry.
     * @param list - original list.
     * @param rows - count of lines in array.
     * @return int[][] - array.
     */
    public int[][] toArray (List<Integer> list, int rows) {
        int cols;

        if (list.size() % rows == 0) {
            cols = list.size()/rows;
        } else {
            cols = list.size()/rows + 1;
        }

        int[][] array = new int[cols][rows];
        int index = 0;
        for (int[] outer : array) {
            for (int i = 0; i < array.length; i++) {
                if (index > list.size() - 1) {
                    outer[i] = 0;
                } else {
                   outer[i] = list.get(index++);
                }
            }
        }
        return array;
    }
}