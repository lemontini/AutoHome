package com.example.autohome;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Device {
    private DeviceClass device;

    @JsonProperty("device")
    public DeviceClass getDevice() { return device; }
    @JsonProperty("device")
    public void setDevice(DeviceClass value) { this.device = value; }
}
