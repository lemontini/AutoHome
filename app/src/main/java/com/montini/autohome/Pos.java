package com.montini.autohome;

import com.fasterxml.jackson.annotation.*;

public class Pos {
    private long position;
    private long tilt;

    @JsonProperty("position")
    public long getPosition() { return position; }
    @JsonProperty("position")
    public void setPosition(long value) { this.position = value; }

    @JsonProperty("tilt")
    public long getTilt() { return tilt; }
    @JsonProperty("tilt")
    public void setTilt(long value) { this.tilt = value; }
}