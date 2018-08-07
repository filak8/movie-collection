package com.example.lenovo.itrextesttask.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lenovo.itrextesttask.models.Film;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "uuid text,"
                + "uri text,"
                + "name text,"
                + "name_eng text,"
                + "premiere text,"
                + "description text" + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void fillDataBase(ArrayList<Film> films) {

        SQLiteDatabase database = getWritableDatabase();

        for (int i = 0; i < films.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("uuid", i);
            contentValues.put("uri", films.get(i).getImage());
            contentValues.put("name", films.get(i).getName());
            contentValues.put("name_eng", films.get(i).getName_eng());
            contentValues.put("premiere", films.get(i).getPremiere());
            contentValues.put("description", films.get(i).getDescription());

            long rowID = database.insert("mytable", null, contentValues);
        }

        close();
    }

    public void fillOrUpdateDataBase(ArrayList<Film> films) {

        SQLiteDatabase database = getWritableDatabase();

        if (database.delete("mytable", null, null) != 0) {

            database.delete("mytable", null, null);
            fillDataBase(films);
        } else {
            fillDataBase(films);
        }

    }

    public ArrayList<Film> getFilmsFromDataBase() {

        SQLiteDatabase database = getWritableDatabase();

        Cursor cursor = database.query("mytable", null, null, null, null, null, null);

        ArrayList<Film> result = new ArrayList<>();
        if (cursor.moveToFirst()) {

            int uriColIndex = cursor.getColumnIndex("uri");
            int nameColIndex = cursor.getColumnIndex("name");
            int nameEngColIndex = cursor.getColumnIndex("name_eng");
            int premiereColindex = cursor.getColumnIndex("premiere");
            int descriptionColIndex = cursor.getColumnIndex("description");

            do {
                Film film = new Film();
                film.setName(cursor.getString(nameColIndex));
                film.setName_eng(cursor.getString(nameEngColIndex));
                film.setImage(cursor.getString(uriColIndex));
                film.setPremiere(cursor.getString(premiereColindex));
                film.setDescription(cursor.getString(descriptionColIndex));

                result.add(film);
            } while (cursor.moveToNext());
        } else cursor.close();

        close();
        return result;
    }
}
