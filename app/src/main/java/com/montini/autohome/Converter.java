// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//     com.fasterxml.jackson.core : jackson-databind : 2.9.0
//
// Import this package:
//
//     import com.android.autohome.Converter;
//
// Then you can deserialize a JSON string with
//
//     Device data = Converter.DeviceFromJsonString(jsonString);
//     Shutter data = Converter.ShutterFromJsonString(jsonString);

package com.montini.autohome;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Converter {
    // Serialize/deserialize helpers

    public static Device DeviceFromJsonString(String json) throws IOException {
        return getDeviceObjectReader().readValue(json);
    }

    public static String DeviceToJsonString(Device obj) throws JsonProcessingException {
        return getDeviceObjectWriter().writeValueAsString(obj);
    }

    public static Shutter ShutterFromJsonString(String json) throws IOException {
        return getShutterObjectReader().readValue(json);
    }

    public static String ShutterToJsonString(Shutter obj) throws JsonProcessingException {
        return getShutterObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader DeviceReader;
    private static ObjectWriter DeviceWriter;

    private static void instantiateDeviceMapper() {
        ObjectMapper mapper = new ObjectMapper();
        DeviceReader = mapper.reader(Device.class);
        DeviceWriter = mapper.writerFor(Device.class);
    }

    private static ObjectReader getDeviceObjectReader() {
        if (DeviceReader == null) instantiateDeviceMapper();
        return DeviceReader;
    }

    private static ObjectWriter getDeviceObjectWriter() {
        if (DeviceWriter == null) instantiateDeviceMapper();
        return DeviceWriter;
    }

    private static ObjectReader ShutterReader;
    private static ObjectWriter ShutterWriter;

    private static void instantiateShutterMapper() {
        ObjectMapper mapper = new ObjectMapper();
        ShutterReader = mapper.reader(Shutter.class);
        ShutterWriter = mapper.writerFor(Shutter.class);
    }

    private static ObjectReader getShutterObjectReader() {
        if (ShutterReader == null) instantiateShutterMapper();
        return ShutterReader;
    }

    private static ObjectWriter getShutterObjectWriter() {
        if (ShutterWriter == null) instantiateShutterMapper();
        return ShutterWriter;
    }
}