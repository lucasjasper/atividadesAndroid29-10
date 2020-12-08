package com.example.listadetarefas2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> lista;
    ArrayAdapter<String> adapter;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listTarefa = findViewById(R.id.listTarefa);
        Button botaoIncluir = findViewById(R.id.botaoIncluir);

        final EditText inputTarefa = findViewById(R.id.inputTarefa);

        lista = new ArrayList<String>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lista);
        listTarefa.setAdapter(adapter);

        botaoIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.notifyDataSetChanged();

                String novaTarefa = inputTarefa.getText().toString();
                lista.add(novaTarefa);

                inputTarefa.setText("");

                adapter.notifyDataSetChanged();

            }
        });

        listTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                i++;
                final int posItem = position;

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        if (i == 2) {

                            new AlertDialog.Builder(MainActivity.this).setTitle("CONCLUIR").setMessage("Concluir tarefa?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    lista.remove(posItem);
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(MainActivity.this, "Tarefa " + inputTarefa.getText().toString() + " concluida", Toast.LENGTH_SHORT).show();

                                }

                            }).setNegativeButton("No", null).show();

                        }

                        i = 0;

                    }

                }, 500);

            }

        });
    }

}