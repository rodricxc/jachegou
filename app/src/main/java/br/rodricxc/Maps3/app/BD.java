package br.rodricxc.Maps3.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BD {
    private SQLiteDatabase bd;

    public BD(Context context) {
        BDCore bdCore = new BDCore(context);
        bd = bdCore.getWritableDatabase();
    }
    public void inserir(Lugar lugar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", lugar.getNome());
        contentValues.put("endereco", lugar.getEndereco());
        contentValues.put("latitude", lugar.getLatitude());
        contentValues.put("longitude", lugar.getLongitude());

        bd.insert("lugares", null, contentValues);
    }
    public void atualizar(Lugar lugar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", lugar.getNome());
        contentValues.put("endereco", lugar.getEndereco());
        contentValues.put("latitute", lugar.getLatitude());
        contentValues.put("longitude", lugar.getLongitude());

        bd.update("lugares", contentValues, "_id = " + lugar.getId(), null);
    }
    public void deletar(Lugar lugar) {
        bd.delete("lugares", "_id = "+lugar.getId(), null);
    }
    public List<Lugar> buscar() {
        List<Lugar> list = new ArrayList<Lugar>();
        String[] colunas = new String[]{"_id", "nome", "endereco", "latitude", "longitude"};
        Cursor cursor = bd.query("lugares", colunas, null, null, null, null, "nome ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Lugar lugar = new Lugar();
                lugar.setId(cursor.getLong(0));
                lugar.setNome(cursor.getString(1));
                lugar.setEndereco(cursor.getString(2));
                lugar.setLatitude(cursor.getDouble(3));
                lugar.setLongitude(cursor.getDouble(4));
                list.add(lugar);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
