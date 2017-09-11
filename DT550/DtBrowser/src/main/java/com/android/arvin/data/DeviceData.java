package com.android.arvin.data;

import java.util.List;

/**
 * Created by tuoyi on 2017/9/10 0010.
 */

public class DeviceData {

    private String deviceName;
    private String strSerial;
    private String strTestTime;
    private boolean deviceRunningStatus;
    private boolean waterStatus;
    private List<SubItemData> subItemTestList;
    private List<HistoricalData> subIitemHisDataList;

    public DeviceData(String strSerial, List<HistoricalData> subIitemHisDataList) {
        this.strSerial = strSerial;
        this.subIitemHisDataList = subIitemHisDataList;
    }

    public DeviceData(String deviceName,
                      String strSerial,
                      String strTestTime,
                      boolean deviceRunningStatus,
                      boolean waterStatus,
                      List<SubItemData> subItemTestList) {
        this.deviceName = deviceName;
        this.strSerial = strSerial;
        this.strTestTime = strTestTime;
        this.deviceRunningStatus = deviceRunningStatus;
        this.waterStatus = waterStatus;
        this.subItemTestList = subItemTestList;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStrSerial() {
        return strSerial;
    }

    public void setStrSerial(String strSerial) {
        this.strSerial = strSerial;
    }

    public String getStrTestTime() {
        return strTestTime;
    }

    public void setStrTestTime(String strTestTime) {
        this.strTestTime = strTestTime;
    }

    public boolean isDeviceRunningStatus() {
        return deviceRunningStatus;
    }

    public void setDeviceRunningStatus(boolean deviceRunningStatus) {
        this.deviceRunningStatus = deviceRunningStatus;
    }

    public boolean isWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(boolean waterStatus) {
        this.waterStatus = waterStatus;
    }

    public List<SubItemData> getSubItemTestList() {
        return subItemTestList;
    }

    public void setSubItemTestList(List<SubItemData> subItemTestList) {
        this.subItemTestList = subItemTestList;
    }

    public List<HistoricalData> getSubIitemHisDataList() {
        return subIitemHisDataList;
    }

    public void setSubIitemHisDataList(List<HistoricalData> subIitemHisDataList) {
        this.subIitemHisDataList = subIitemHisDataList;
    }
}
