package br.rodricxc.Maps3.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonHistorico = (Button) findViewById(R.id.button_historico);

        buttonHistorico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goHistorico();
            }
        });

        Button buttonMeuLocal = (Button) findViewById(R.id.button_meulocal);

        buttonMeuLocal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goMyPlace();
            }
        });

        Button buttonConfiguracoes = (Button) findViewById(R.id.prefs);
        buttonConfiguracoes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goConfigs();
            }
        });


    }

    public void goHistorico() {
        Intent i  = new Intent(this, MapsActivity.class);
        startActivity(new Intent(this, MapsActivity.class));
        return;
    }


    public void goMyPlace() {
        Intent i  = new Intent(this, MeuLocal.class);
        startActivity(new Intent(this, MeuLocal.class));
        return;
    }

    public void goConfigs() {
        Intent i = new Intent(this, ConfigActivity.class);
        startActivity(new Intent(this, ConfigActivity.class));
    }
}
