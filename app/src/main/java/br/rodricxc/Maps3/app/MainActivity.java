package br.rodricxc.Maps3.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab lugares = actionBar.newTab();
        lugares.setText("Lugares");
        lugares.setTabListener(new TabListener(new LugaresFragment()));
        actionBar.addTab(lugares);
        ActionBar.Tab historico = actionBar.newTab();
        historico.setText("Histórico");
        historico.setTabListener(new TabListener(new HistoricoFragment()));
        actionBar.addTab(historico);
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
                startActivity(new Intent(this, NovoLocal.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, ConfigActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class TabListener implements ActionBar.TabListener {
        private Fragment fragment;
        public TabListener(Fragment fragment) {
            this.fragment = fragment;
        }
        @Override
        public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment);
            ft.commit();
        }
        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
        @Override
        public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

        }
    }
}
