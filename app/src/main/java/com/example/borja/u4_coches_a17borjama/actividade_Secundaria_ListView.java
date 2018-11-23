package com.example.borja.u4_coches_a17borjama;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;



public class actividade_Secundaria_ListView extends Activity {

    private ListView listView;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria_listview);
        listView = findViewById(R.id.lvLista);
        file = new File(getIntent().getExtras().getString("ruta"));
        try {
            poblarListView(Utils.leerFichero(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void poblarListView(String[] values){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                String  itemValue = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Posición :"+itemPosition+"  Elemento: " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }

        });
    }

    public void volver(View v) {
        finish();
    }
}
