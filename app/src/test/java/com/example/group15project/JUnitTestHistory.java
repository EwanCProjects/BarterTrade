package com.example.group15project;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTestHistory {

    static HistoryActivity historyActivity;

    @BeforeClass
    public static void setup() {
        historyActivity = new HistoryActivity();
    }





    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
