package com.example.borja.u4_coches_a17borjama;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;



public class actividade_Secundaria_ListView extends Activity {

    private ListView listView;
    private String rutaCompleta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria_listview);
        empezarList();
    }

    public void empezarList() {
        listView = findViewById(R.id.lvLista);
        rutaCompleta = getIntent().getExtras().getString("ruta");
        try {
            crearList(leerFichero(rutaCompleta));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void crearList(String[] values){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition     = position;

                String  itemValue    = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Posición :"+itemPosition+"  Elemento: " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

    public static String[] leerFichero(String rutaCompleta) throws  Exception{
        String rutaFichero=rutaCompleta;
        int arr=0;
        int lNumeroLineas=0;
        String[] array;

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
            return values;
    }

    public void cambiarLayout(View v) {
        finish();
    }
}
