package com.montini.autohome;

import com.fasterxml.jackson.annotation.*;

public class Device {
    private DeviceClass device;
    private Shutter shutter;

    @JsonProperty("device")
    public DeviceClass getDevice() { return device; }
    @JsonProperty("device")
    public void setDevice(DeviceClass value) { this.device = value; }

    @JsonProperty("shutter")
    public Shutter getShutter() { return shutter; }
    @JsonProperty("shutter")
    public void setShutter(Shutter value) { this.shutter = value; }
}
