package com.equivi.channel.tracker;


public enum ClientDeviceType {

    MOBILE("mobile"),
    TABLET("tablet"),
    DESKTOP("desktop");

    private String clientDeviceType;

    ClientDeviceType(String filterName) {
        this.clientDeviceType = filterName;
    }

    public String getClientDeviceType() {
        return clientDeviceType;
    }
}
