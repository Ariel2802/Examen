package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.PlaceHolderView;

import controller.JournalHolder;
import model.Journal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    PlaceHolderView placeJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeJournal = findViewById(R.id.placeJournal);
        requestQueue = Volley.newRequestQueue(this);

        traerRevistas();
    }

    private void traerRevistas() {
        String url = "https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int tamanio = response.length();
                        if (tamanio > 0) {
                            for (int i = 0; i < tamanio; i++) {
                                try {
                                    JSONObject json = new JSONObject(response.get(i).toString());
                                    Journal journal = new Journal(json.getInt("journal_id"),
                                            json.getString("portada"), json.getString("abbreviation"),
                                            json.getString("description"), json.getString("journalThumbnail"),
                                            json.getString("name"));
                                    JournalHolder journalHolder = new JournalHolder(MainActivity.this, journal);
                                    placeJournal.addView(journalHolder);
                                } catch (JSONException ex) {
                                    Journal journal = new Journal(0,
                                            "https://cdn3.josefacchin.com/wp-content/uploads/2018/09/http-not-found-error-404.png",
                                            "No hay resultados", "No hay resultados",
                                            "No hay resultados", "Sin nombres");
                                    JournalHolder journalHolder = new JournalHolder(MainActivity.this, journal);
                                    placeJournal.addView(journalHolder);
                                    System.out.println(ex.toString());
                                }
                            }
                        } else {
                            Journal journal = new Journal(0,
                                    "https://cdn3.josefacchin.com/wp-content/uploads/2018/09/http-not-found-error-404.png",
                                    "No hay resultados", "No hay resultados",
                                    "No hay resultados", "Sin nombres");
                            JournalHolder journalHolder = new JournalHolder(MainActivity.this, journal);
                            placeJournal.addView(journalHolder);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError ex) {
                Journal journal = new Journal(0,
                        "https://cdn3.josefacchin.com/wp-content/uploads/2018/09/http-not-found-error-404.png",
                        "No hay resultados", "No hay resultados",
                        "No hay resultados", "Sin nombres");
                JournalHolder journalHolder = new JournalHolder(MainActivity.this, journal);
                placeJournal.addView(journalHolder);
                System.out.println(ex.toString());
            }
        });
        requestQueue.add(jsonRequest);
    }

}