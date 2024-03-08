package com.project.PFEBackEnd.entities.dto;

public class DeviceCount {
    private String deviceType;
    private int count;

    public DeviceCount(String deviceType, int count) {
        this.deviceType = deviceType;
        this.count = count;
    }


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
