package com.montini.autohome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String dev_status[] = {"{\n" +
            "    \"device\": {\n" +
            "        \"deviceName\": \"blind1\",\n" +
            "        \"type\": \"shutterBox\",\n" +
            "        \"fv\": \"0.176\",\n" +
            "        \"hv\": \"0.6\",\n" +
            "        \"apiLevel\": \"20180604\",\n" +
            "        \"id\": \"1afe34db9437\",\n" +
            "        \"ip\": \"192.168.1.103\"\n" +
            "    }\n" +
            "}",
            "{\n" +
                    "    \"device\": {\n" +
                    "        \"deviceName\": \"blind2\",\n" +
                    "        \"type\": \"shutterBox\",\n" +
                    "        \"fv\": \"0.176\",\n" +
                    "        \"hv\": \"0.6\",\n" +
                    "        \"apiLevel\": \"20180604\",\n" +
                    "        \"id\": \"1afe34db9437\",\n" +
                    "        \"ip\": \"192.168.1.105\"\n" +
                    "    }\n" +
                    "}",
            "{\n" +
                    "    \"device\": {\n" +
                    "        \"deviceName\": \"blind3\",\n" +
                    "        \"type\": \"shutterBox\",\n" +
                    "        \"fv\": \"0.176\",\n" +
                    "        \"hv\": \"0.6\",\n" +
                    "        \"apiLevel\": \"20180604\",\n" +
                    "        \"id\": \"1afe34db9437\",\n" +
                    "        \"ip\": \"192.168.1.102\"\n" +
                    "    }\n" +
                    "}",
            "{\n" +
            "    \"device\": {\n" +
            "        \"deviceName\": \"blind4\",\n" +
            "        \"type\": \"shutterBox\",\n" +
            "        \"fv\": \"0.176\",\n" +
            "        \"hv\": \"0.6\",\n" +
            "        \"apiLevel\": \"20180604\",\n" +
            "        \"id\": \"1afe34db9437\",\n" +
            "        \"ip\": \"192.168.1.106\"\n" +
            "    }\n" +
            "}"};
    String sh_status[] = {
            "{\n" +
            "    \"shutter\": {\n" +
            "        \"state\": 3,\n" +
            "        \"currentPos\": {\n" +
            "            \"position\": 100,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"desiredPos\": {\n" +
            "            \"position\": 0,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"favPos\": {\n" +
            "            \"position\": 40,\n" +
            "            \"tilt\": -1\n" +
            "        }\n" +
            "    }\n" +
            "}",
            "{\n" +
            "    \"shutter\": {\n" +
            "        \"state\": 2,\n" +
            "        \"currentPos\": {\n" +
            "            \"position\": 59,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"desiredPos\": {\n" +
            "            \"position\": 59,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"favPos\": {\n" +
            "            \"position\": 50,\n" +
            "            \"tilt\": -1\n" +
            "        }\n" +
            "    }\n" +
            "}",
            "{\n" +
            "    \"shutter\": {\n" +
            "        \"state\": 2,\n" +
            "        \"currentPos\": {\n" +
            "            \"position\": 65,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"desiredPos\": {\n" +
            "            \"position\": 65,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"favPos\": {\n" +
            "            \"position\": 60,\n" +
            "            \"tilt\": -1\n" +
            "        }\n" +
            "    }\n" +
            "}",
            "{\n" +
            "    \"shutter\": {\n" +
            "        \"state\": 2,\n" +
            "        \"currentPos\": {\n" +
            "            \"position\": 2,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"desiredPos\": {\n" +
            "            \"position\": 2,\n" +
            "            \"tilt\": -1\n" +
            "        },\n" +
            "        \"favPos\": {\n" +
            "            \"position\": 70,\n" +
            "            \"tilt\": -1\n" +
            "        }\n" +
            "    }\n" +
            "}"};


    String shutters[] = {"192.168.1.103", "192.168.1.105", "192.168.1.102", "192.168.1.106"};
    static ArrayList<RollerBlinds> rb = new ArrayList<>();
    int settings[][] = {{0, 0, 0, 0}, {99, 100, 0, 0}, {99, 100, 100, 99}};
    public String answer = "";

    ImageButton iBtnAllOff, iBtnBreakfast, iBtnSleep, iBtnAddProfile, iBtnStop;
    TextView infoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defDevices();

        saveDevices();

        loadDevices();

        infoBox = findViewById(R.id.textView);
        iBtnAllOff = findViewById(R.id.imageButton1);
        iBtnBreakfast = findViewById(R.id.imageButton2);
        iBtnSleep = findViewById(R.id.imageButton3);
        iBtnAddProfile = findViewById(R.id.imageButton0);
        iBtnStop = findViewById(R.id.imageButton5);

        // String allIPs = "";
        // for (RollerBlinds i : rb) {
        //     allIPs += i.ip + " | ";
        // }
        // infoBox.setText(allIPs);
    }

    public void btn0_Click(View v) {

        String url;
        Shutter sh;
        for (int i = 0; i < 4; i++) {
            try {
                sh = Converter.ShutterFromJsonString(sh_status[i]);
                url = "http://" + shutters[i] + "/s/p/" + sh.getShutter().getCurrentPos().getPosition();
                String answ = commREST(url);
                Log.e("sh", url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void btn5_Click(View v) {
        Intent intentAddDevice = new Intent(this, DeviceView.class);
        Bundle bundle = new Bundle();
        // bundle.putParcelableArrayList("devices",rb);
        intentAddDevice.putExtra("devices", rb);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intentAddDevice);
    }

    public void btn1_Click(View v) {

        String buttonId = getResources().getResourceEntryName(v.getId());
        int n = Integer.valueOf(buttonId.substring(buttonId.length() - 1)) - 1;

        Toast.makeText(this, "Button" + (n + 1) + " clicked. Sending command", Toast.LENGTH_SHORT).show();

        String url;
        for (int i = 0; i < 4; i++) {
            url = "http://" + shutters[i] + "/s/p/" + settings[n][i];
            String answ = commREST(url);
            Log.e("sh1", url);
        }
        infoBox.setText(answer);
    }

    // public Object checkDevice(Object obj) {
    //     // try {
    //     //     Device d1 = Converter.DeviceFromJsonString(dev_answer);
    //     //     if (d1.getDevice().getType().equals("shutterBox")) {
    //     //         Shutter sh1 = Converter.ShutterFromJsonString(sh_answer);
    //     //         Log.e("info", String.valueOf(sh1.getShutter().getFavPos().getPosition()));
    //     //     }
    //     //     Log.e("info", d1.getDevice().getIP());
    //     // } catch (IOException e) {
    //     //     e.printStackTrace();
    //     // }
    //
    //     return obj;
    // }

    public String commREST(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //e.printStackTrace();
                Log.e("error", "Request failed" + "| " + e.getMessage());
                answer = e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    //final String myResponse = response.body().string();
                    answer = response.body().string();
                    // final String myResponse;
                    // try {
                    //     myResponse = response.body().string();
                    //     MainActivity.this.runOnUiThread(new Runnable() {
                    //         @Override
                    //         public void run() {
                    //             infoBox.setText(myResponse);
                    //         }
                    //     });
                    //     Log.e("interaction", "Response OK");
                    //
                    // } catch (IOException e) {
                    //     e.printStackTrace();
                    //     Log.e("error", e.getMessage());
                    // }
                    // Toast.makeText(MainActivity.this, "Command sent", Toast.LENGTH_LONG).show();
                    // Log.e("interaction", "Command sent");
                }

            }
        });

        return answer;

    }

    public void defDevices() {
        // for (int i = 0; i < dev_status.length; i++) {
        //     try {
        //         // Shutter sh = Converter.ShutterFromJsonString(sh_answer);
        //         Shutter sh =
        //         rb.add(sh);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
        rb.add(new RollerBlinds("192.168.1.102"));
        rb.add(new RollerBlinds("192.168.1.103"));
        rb.add(new RollerBlinds("192.168.1.105"));
        rb.add(new RollerBlinds("192.168.1.106"));
        ArrayList<Device> dev = new ArrayList<>();
        // TODO: dev.add(new DeviceClass());
    }

    public void saveDevices() {
        SharedPreferences spref = getSharedPreferences("com.montini.autohome", MODE_PRIVATE);
        for (int i = 0; i < rb.size(); i++) {
            spref.edit().putString("dev" + i, rb.get(i).getIP()).apply();
        }
    }

    public void loadDevices() {
        SharedPreferences spref = getSharedPreferences("com.montini.autohome", MODE_PRIVATE);
        for (int i = 0; i < rb.size(); i++) {
            spref.edit().putString("dev" + i, rb.get(i).getIP()).apply();
        }
    }

    class RollerBlinds {

        private String ip;
        private int currPos;

        public RollerBlinds(String ip) {
            this.ip = ip;
            this.currPos = 0;
            System.out.println(this.ip + " | " + this.currPos);
        }

        public RollerBlinds(String ip, int currPos) {
            this.ip = ip;
            this.currPos = currPos;
            System.out.println(this.ip + " | " + this.currPos);
        }


        public String getIP() {
            return this.ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getCurrPos() {
            return this.currPos;
        }

        public void setCurrPos(int currPos) {
            this.currPos = currPos;
        }
    }

}