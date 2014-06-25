package br.rodricxc.Maps3.app;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class LugaresFragment extends ListFragment {
/*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, ""+numbers_digits[(int) id], Toast.LENGTH_LONG).show();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BD bd = new BD(getActivity());
        List<Lugar> lista = bd.buscar();
        //String[] lugares = new String[lista.size()];
        ArrayList<HashMap<String, String>> lugares = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < lista.size(); i++) {
            //lugares.set(i, lista.get(i).getNome());
            HashMap<String, String> lugar = new HashMap<String, String>();
            lugar.put("nome", lista.get(i).getNome());
            lugar.put("endereco", lista.get(i).getEndereco());
            lugares.add(lugar);
        }
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, lugares);
        String[] from = new String[]{"nome", "endereco"};
        int layout = android.R.layout.simple_list_item_2;
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), lugares, layout, from, to);
        setListAdapter(adapter);
        View view = inflater.inflate(R.layout.fragment_lugares,
                container, false);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
