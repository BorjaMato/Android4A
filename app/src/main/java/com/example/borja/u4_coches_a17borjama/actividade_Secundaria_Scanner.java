package com.example.borja.u4_coches_a17borjama;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class actividade_Secundaria_Scanner extends Activity {

    private Spinner spCoches;
    private String rutaCompleta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria_spinner);
        empezarList();
    }

    public void empezarList() {
        spCoches = findViewById(R.id.spin_coches);
        rutaCompleta = getIntent().getExtras().getString("ruta");
        try {
            crearSpinner(actividade_Secundaria_ListView.leerFichero(rutaCompleta));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearSpinner(String[] values){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCoches.setAdapter(adapter);


        spCoches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


                int itemPosition     = position;


                String  itemValue    = (String) spCoches.getItemAtPosition(position);


                Toast.makeText(getApplicationContext(),
                        "Posición :"+itemPosition+"  Elemento: " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    public void leerFichero(String rutaCompleta){
        String rutaFichero=rutaCompleta;
        int arr=0;
        int lNumeroLineas=0;
        try{
            File ruta=new File(rutaFichero);
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
            BufferedReader brr=new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
            String datos;
            String lineas;
            while ((lineas= br.readLine())!=null) {
                lNumeroLineas++;
            }

            Log.i("lineas","Dato:"+lNumeroLineas);
            String[] values=new String [lNumeroLineas];

            while((datos=brr.readLine())!=null){
                values[arr]=datos;
                Log.e("datos","Dato:"+values[arr]);
                arr++;
            }
            br.close();
            brr.close();
            crearSpinner(values);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void cambiarLayout(View v) {
        finish();
    }
}
