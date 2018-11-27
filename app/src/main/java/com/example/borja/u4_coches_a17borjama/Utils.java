package com.example.borja.u4_coches_a17borjama;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String[] leerFichero(File fichero) throws  Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fichero)));
        String[] values=new String[0];
        List<String> listaLineas = new ArrayList<>();
        String linea="";
        while((linea=br.readLine())!=null){
            Log.e("datos","Dato:"+ linea);
            listaLineas.add(linea);
        }
        br.close();
        values = listaLineas.toArray(values);
        return values;
    }
}
