package com.morpheus.manejoarchivos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{
    private Button btCrear, btLeer;
    private TextView txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCrear = (Button)findViewById(R.id.btCrear);
        btLeer = (Button)findViewById(R.id.btLeer);
        txtTexto = (TextView)findViewById(R.id.txtTexto);

        btCrear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String archivo = "ejemplo.csv";
                String contenido = "Titulo\r\nEjemplo";

                //Crea un objeto FileOutput para crear el archivo y le coloca contenido
                FileOutputStream outputStream = null;
                try
                {
                    outputStream = openFileOutput(archivo, Context.MODE_PRIVATE);
                    outputStream.write(contenido.getBytes());
                    outputStream.close();
                } catch (java.io.IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        btLeer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Se prepara para leer el archivo
                try
                {
                    FileInputStream fileInputStream = getApplicationContext().openFileInput("ejemplo.csv");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder = new StringBuilder();
                    String linea;

                    while((linea = bufferedReader.readLine()) != null)
                        stringBuilder.append(linea).append("\n");

                    txtTexto.setText(stringBuilder.toString());
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
