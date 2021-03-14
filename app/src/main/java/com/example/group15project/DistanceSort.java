package com.example.group15project;
import java.util.Comparator;

/**
 * defines how posts are to be sorted
 */

class DistanceSort implements Comparator<Post> {
    public static String sortName = "Distance"; //displayed to user on "sort by" spinner
    private int ascending;

    public DistanceSort(boolean ascending) {
        if(ascending){
            this.ascending = 1;
        }else {
            this.ascending = -1;
        }
    }

    @Override
    public int compare(Post t1, Post t2) {
        return Float.compare(t1.getDistance(), t2.getDistance()) * ascending;
    }

}
