package com.example.autohome;

import com.fasterxml.jackson.annotation.*;

public class DeviceClass {
    private String deviceName;
    private String type;
    private String fv;
    private String hv;
    private String apiLevel;
    private String id;
    private String ip;

    @JsonProperty("deviceName")
    public String getDeviceName() { return deviceName; }
    @JsonProperty("deviceName")
    public void setDeviceName(String value) { this.deviceName = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("fv")
    public String getFv() { return fv; }
    @JsonProperty("fv")
    public void setFv(String value) { this.fv = value; }

    @JsonProperty("hv")
    public String getHv() { return hv; }
    @JsonProperty("hv")
    public void setHv(String value) { this.hv = value; }

    @JsonProperty("apiLevel")
    public String getAPILevel() { return apiLevel; }
    @JsonProperty("apiLevel")
    public void setAPILevel(String value) { this.apiLevel = value; }

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("ip")
    public String getIP() { return ip; }
    @JsonProperty("ip")
    public void setIP(String value) { this.ip = value; }
}
