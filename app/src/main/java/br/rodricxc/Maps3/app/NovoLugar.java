package br.rodricxc.Maps3.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.internal.cu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class NovoLugar extends ActionBarActivity {
    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private Marker marker;
    private Lugar lugar = new Lugar();
    private EditText nomeEt;
    private EditText enderecoEt;
    private AlertDialog alerta;
    //private EditText latitudeEt;
    //private EditText longitudeEt;
    //private Button buscarBr;
    //private Button salvarBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_lugar);
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
                nomeEt.setText(lugar.getNome());
                enderecoEt.setText(lugar.getEndereco());
            }
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.novo_lugar_map);
        map = mapFragment.getMap();

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
                if (verificarSalvar()) {
                    salvarLugar();
                }
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, ConfigActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getLocation(View view) {
        Geocoder geocoder = new Geocoder(NovoLugar.this);

        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocationName(enderecoEt.getText().toString(), 1);
            if (addressList.size() >= 1) {
                String nome = nomeEt.getText().toString();
                if (nome.length() == 0) {
                    nome = "Lugar";
                }
                String endereco = addressList.get(0).getAddressLine(0) + ", " + addressList.get(0).getAddressLine(1);
                LatLng latLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());

                customAddMarker(latLng, nome, endereco);
            } else {
                Toast.makeText(NovoLugar.this, R.string.lugar_desconhecido, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(NovoLugar.this, R.string.conexao_internet, Toast.LENGTH_LONG).show();
        }
    }
    public void customAddMarker(LatLng latLng, String title, String snippet) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title(title).snippet(snippet).draggable(true);
        if (marker != null) {
            marker.remove();
        }
        marker = map.addMarker(markerOptions);
        marker.showInfoWindow();
    }

    private void alertaErro(String titulo, String mensagem) { //Cria o gerador do AlertDialog
        AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.setTitle(titulo); //define a mensagem
        builder.setMessage(mensagem); //define um botão como positivo
        builder.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //builder.setIcon(R.drawable.ic_);
        builder.show(); //define um botão como negativo. builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() { public void onClick(DialogInterface arg0, int arg1) { Toast.makeText(MainActivity.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show(); } }); //cria o AlertDialog alerta = builder.create(); //Exibe alerta.show(); }

    }

    public boolean verificarSalvar(){
        boolean ver = true;
        String mensagem = "";
        if (nomeEt.length()==0) {
            mensagem = mensagem + "Defina um nome para o local. ";
            ver = false;
        }

        if(marker==null) {
            mensagem = mensagem + "Digite um local e utilize o botão pesquisar para definir um local.";
            ver = false;
        }
        if (!ver){
            alertaErro("Erro",mensagem);
        }
        return ver;
    }

    public void salvarLugar(/*View view*/) {
        lugar.setNome(marker.getTitle());
        lugar.setEndereco(marker.getSnippet());
        lugar.setLatitude(marker.getPosition().latitude);
        lugar.setLongitude(marker.getPosition().longitude);

        BD bd = new BD(this);
        bd.inserir(lugar);

        Toast.makeText(this, "Lugar inserido com sucesso!", Toast.LENGTH_LONG).show();
    }
}
