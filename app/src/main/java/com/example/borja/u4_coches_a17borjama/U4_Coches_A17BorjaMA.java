package com.example.borja.u4_coches_a17borjama;

import java.util.Calendar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.View;

public class U4_Coches_A17BorjaMA extends AppCompatActivity {


    private final String NOMEFICHERO = "Coches.txt";

    private boolean sdDisponible = false;
    private boolean sdAccesoEscritura = false;
    private String ruta;
    private File directorioSD;
    private File archivoCoches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u4__coches__a17_borja_m);

        comprobarSD();
        establecerDirectorioFicheiro();
    }

    public void comprobarSD() {
        String estado = Environment.getExternalStorageState();
        Log.e("SD", estado);

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            sdDisponible = true;
    }

    public void establecerDirectorioFicheiro() {

        if (sdDisponible) {
            directorioSD = getExternalFilesDir(null);
            ruta= directorioSD +"/"+NOMEFICHERO;
            archivoCoches = new File(ruta);
        }
    }

    public void engadirSobrescribir(Boolean x){
        EditText etTexto = findViewById(R.id.etCoches);
        boolean engSobre=x;
        Calendar time=Calendar.getInstance();
        if(etTexto.getText().toString().equals("")) {

        }else{
            if (sdAccesoEscritura) {
                try {
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(archivoCoches, engSobre));
                    Log.i("ruta", ""+archivoCoches.getAbsolutePath());
                    osw.write(etTexto.getText() + "-" + time.getTime()+"\n");
                    Log.i("escrito",""+etTexto.getText() + "-" + time.getTime());
                    osw.close();
                    etTexto.setText("");
                } catch (Exception ex) {
                    Log.i("SD", "Error escribindo no ficheiro");
                }
            } else {
                Toast.makeText(this, "A tarxeta SD non está en modo acceso escritura", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnEngSobre(View v) {
        RadioButton rbEng=findViewById(R.id.rbtn_Eng);
        if(rbEng.isChecked()==true) {
            engadirSobrescribir(true);
        }else{
            engadirSobrescribir(false);
        }
    }

    public void btnSecundario(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Selecciona vista");
        builder.setItems(R.array.botonesDialogo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Intent intent = new Intent(getApplicationContext(),actividade_Secundaria_ListView.class);
                    intent.putExtra("ruta",ruta);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(),actividade_Secundaria_Spinner.class);
                    intent.putExtra("ruta",ruta);
                    startActivity(intent);
                }
            }
        });
        builder.create().show();
    }
}
