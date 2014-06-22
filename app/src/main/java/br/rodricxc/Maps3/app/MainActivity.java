package br.rodricxc.Maps3.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                goMyPlace();
                return true;
            case R.id.action_settings:
                goConfigs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
