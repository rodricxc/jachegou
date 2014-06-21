package br.rodricxc.Maps3.app;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MeuLocal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_local);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meu_local, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
