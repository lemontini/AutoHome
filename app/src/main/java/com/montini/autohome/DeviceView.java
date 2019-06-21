package com.montini.autohome;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DeviceView extends AppCompatActivity {

    ListView listView;
    String[] listItem = {"192.168.1.103",
            "192.168.1.105",
            "192.168.1.102",
            "192.168.1.106"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_view);

        listView = (ListView) findViewById(R.id.listView);
        // listItem = getResources().getStringArray(R.array.array_technology);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listrow, R.id.textView, listItem);
        listView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        ArrayList<MainActivity.RollerBlinds> rb = bundle.getParcelable("devices");

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", rb);
        finish();
    }
}
