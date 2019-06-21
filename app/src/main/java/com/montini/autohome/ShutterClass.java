package com.montini.autohome;

import com.fasterxml.jackson.annotation.*;

public class ShutterClass extends Pos {
    private long state;
    private Pos currentPos;
    private Pos desiredPos;
    private Pos favPos;

    @JsonProperty("state")
    public long getState() { return state; }
    @JsonProperty("state")
    public void setState(long value) { this.state = value; }

    @JsonProperty("currentPos")
    public Pos getCurrentPos() { return currentPos; }
    @JsonProperty("currentPos")
    public void setCurrentPos(Pos value) { this.currentPos = value; }

    @JsonProperty("desiredPos")
    public Pos getDesiredPos() { return desiredPos; }
    @JsonProperty("desiredPos")
    public void setDesiredPos(Pos value) { this.desiredPos = value; }

    @JsonProperty("favPos")
    public Pos getFavPos() { return favPos; }
    @JsonProperty("favPos")
    public void setFavPos(Pos value) { this.favPos = value; }
}