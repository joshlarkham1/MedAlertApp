package com.medalert.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.medalert.R;

public class MedicalNumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_numbers);

        ListView listView = findViewById(R.id.listViewNumbers);

        // Sample list of medical numbers
        String[] medicalNumbers = {
                "Emergency Services: 999",
                "NHS 111 Service: 111",
                "Northern Ireland Ambulance Service: 028 9040 0999",
                "Mental Health Helpline: 0808 808 8000"
        };

        // Populate the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medicalNumbers);
        listView.setAdapter(adapter);
    }
}
