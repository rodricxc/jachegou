package br.rodricxc.Maps3.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HistoricoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(getActivity());
        BD bd = new BD(getActivity());
        List<Lugar> lista = bd.buscar();
        String string = "";
        for (int i = 0; i < lista.size(); i++) {
            string += "\n" + lista.get(i).getNome();
        }
        view.setText(string);
        return view;
    }
}
