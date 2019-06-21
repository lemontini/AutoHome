package com.montini.autohome;

import com.fasterxml.jackson.annotation.*;

public class Shutter {
    private ShutterClass shutter;

    @JsonProperty("shutter")
    public ShutterClass getShutter() { return shutter; }
    @JsonProperty("shutter")
    public void setShutter(ShutterClass value) { this.shutter = value; }
}