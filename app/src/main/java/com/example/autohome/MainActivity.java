package com.example.autohome;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

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

        String allIPs = "";
        for (RollerBlinds i : rb) {
            allIPs += i.ip + " | ";
        }
        infoBox.setText(allIPs);
    }

    public void btn0_Click(View v) {
    }

    public void btn5_Click(View v) {
    }

    public void btn1_Click(View v) {
        // Toast.makeText(this, "Sending command", Toast.LENGTH_SHORT).show();
        // Log.i("interaction", "Sending command");

        String url;
        for (int i = 0; i < 4; i++) {
            url = "http://" + shutters[i] + "/s/p/" + settings[0][i];
            String answ = commREST(url);
        }
        //url = "http://192.168.1.105/s/p/0";
        //String answ = commREST(url);
        infoBox.setText(answer);
    }

    public void btn2_Click(View v) {
        String url;
        for (int i = 0; i < 4; i++) {
            url = "http://" + shutters[i] + "/s/p/" + settings[1][i];
            String answ = commREST(url);
        }
        infoBox.setText(answer);
    }

    public void btn3_Click(View v) {
        String url;
        for (int i = 0; i < 4; i++) {
            url = "http://" + shutters[i] + "/s/p/" + settings[2][i];
            String answ = commREST(url);
        }
        infoBox.setText(answer);
    }

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
        rb.add(new RollerBlinds("192.168.1.102"));
        rb.add(new RollerBlinds("192.168.1.103"));
        rb.add(new RollerBlinds("192.168.1.105"));
        rb.add(new RollerBlinds("192.168.1.106"));
        ArrayList<Device> dev = new ArrayList<>();
        // TODO: dev.add(new DeviceClass());
    }

    public void saveDevices() {
        SharedPreferences spref = getSharedPreferences("com.example.autohome", MODE_PRIVATE);
        for (int i = 0; i < rb.size(); i++) {
            spref.edit().putString("dev" + i, rb.get(i).getIP()).apply();
        }
    }

    public void loadDevices() {
        SharedPreferences spref = getSharedPreferences("com.example.autohome", MODE_PRIVATE);
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