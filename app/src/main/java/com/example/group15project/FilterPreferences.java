package com.example.group15project;

import java.util.ArrayList;

public class FilterPreferences {
    int maxDistance;
    int sortMethodIndex = -1;

    public FilterPreferences() {
    }

    public FilterPreferences(ArrayList<String> categories, int maxDistance, float minPay) {
        this.maxDistance = maxDistance;
    }
    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int getSortMethodIndex() {
        return sortMethodIndex;
    }

    public void setSortMethodIndex(int sortMethodIndex) {
        this.sortMethodIndex = sortMethodIndex;
    }
}
