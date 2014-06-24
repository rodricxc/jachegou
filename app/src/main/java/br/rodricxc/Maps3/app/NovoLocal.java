package br.rodricxc.Maps3.app;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class NovoLocal extends ActionBarActivity {
    private Lugar lugar = new Lugar();
    private EditText nomeEt;
    private EditText enderecoEt;
    //private EditText latitudeEt;
    //private EditText longitudeEt;
    //private Button buscarBr;
    //private Button salvarBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_local);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nomeEt = (EditText) findViewById(R.id.nome);
        enderecoEt = (EditText) findViewById(R.id.endereco);
        //buscarBr = (Button) findViewById(R.id.buscar);
        //salvarBt = (Button) findViewById(R.id.action_save);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                lugar.setId(bundle.getLong("id"));
                lugar.setNome(bundle.getString("nome"));
                lugar.setEndereco(bundle.getString("endereco"));
                //lugar.setLatitude(bundle.getLong("latitute"));
                //lugar.setLongitude(bundle.getLong("longitude"));

                nomeEt.setText(lugar.getNome());
                enderecoEt.setText(lugar.getEndereco());
                //latitudeEt.setText(""+lugar.getLatitude());
                //longitudeEt.setText("" + lugar.getLongitude());
            }
        }


        /*
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Integer tempo = Integer.parseInt(sharedPref.getString(ConfigActivity.KEY_PREF_TEMPO, "0"));
        Integer distancia = Integer.parseInt(sharedPref.getString(ConfigActivity.KEY_PREF_DISTANCIA, "0"));
        Boolean vibrar = sharedPref.getBoolean(ConfigActivity.KEY_PREF_VIBRAR, false);
        String som = sharedPref.getString(ConfigActivity.KEY_PREF_SOM, "");
        Integer atualizacao_gps = Integer.parseInt(sharedPref.getString(ConfigActivity.KEY_PREF_ATUALIZACAO_GPS, "0"));
        TextView texto = (TextView) findViewById(R.id.textView);
        String string = "Tempo para alarme: " + tempo;
        string += "\nDistancia para alarme: " + distancia;
        string += "\nVibrar: " + Boolean.toString(vibrar);
        string += "\nSom:" + som;
        string += "\nAtualizacao GPS: " + atualizacao_gps;
        texto.setText(string);
        */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.novo_local_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_save:
                //startActivity(new Intent(this, MapsActivity.class));
                //salvarLugar();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, ConfigActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void salvarLugar(View view) {
        lugar.setNome(nomeEt.getText().toString());
        lugar.setEndereco(enderecoEt.getText().toString());
        lugar.setLatitude(0);//Integer.parseInt(latitudeEt.getText().toString()));
        lugar.setLongitude(0);//Integer.parseInt(longitudeEt.getText().toString()));

        BD bd = new BD(this);
        bd.inserir(lugar);

        Toast.makeText(this, "Lugar inserido com sucesso!", Toast.LENGTH_LONG).show();
    }
    public void listar(View view) {
        BD bd = new BD(this);
        List<Lugar> lista = bd.buscar();
        if (lista != null) {
            Toast.makeText(this, ""+lista.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "vazio", Toast.LENGTH_SHORT).show();
        }
    }
}
