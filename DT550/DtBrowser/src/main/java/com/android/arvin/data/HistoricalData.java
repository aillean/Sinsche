package com.android.arvin.data;

/**
 * Created by tuoyi on 2017/9/11 0011.
 */

public class HistoricalData {

    String itemCode;
    String testTime;
    String testData;

    public HistoricalData(String testTime, String testData) {
        this.testTime = testTime;
        this.testData = testData;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
