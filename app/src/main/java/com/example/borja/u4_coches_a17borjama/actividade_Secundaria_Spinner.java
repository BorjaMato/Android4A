package com.example.borja.u4_coches_a17borjama;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class actividade_Secundaria_Spinner extends Activity {

    private Spinner spCoches;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria_spinner);
        spCoches = findViewById(R.id.spin_coches);
        file = new File(getIntent().getExtras().getString("ruta"));
        try {
            poblarSpinner(Utils.leerFichero(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void poblarSpinner(String[] values){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCoches.setAdapter(adapter);
        spCoches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition  = position;
                String  itemValue  = (String) spCoches.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Posición :"+itemPosition+"  Elemento: " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void volver(View v) {
        finish();
    }
}
