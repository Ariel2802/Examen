package com.example.examen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import controller.IssueHolder;
import controller.PublicationHolder;
import model.Issue;
import model.Publication;

public class PubActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    PlaceHolderView placePublication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub);
        requestQueue = Volley.newRequestQueue(this);
        placePublication = findViewById(R.id.placePublication);

        Bundle bundle = getIntent().getExtras();
        Issue issue = (Issue) bundle.getSerializable("issue");
        traerArticulos(String.valueOf(issue.getIssue_id()));
    }

    private void traerArticulos(String idIssue) {
        String url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + idIssue;
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int tamanio = response.length();
                        if (tamanio > 0) {
                            for (int i = 0; i < tamanio; i++) {
                                try {
                                    JSONObject json = new JSONObject(response.get(i).toString());
                                    Publication publication = new Publication(json.getInt("publication_id"),
                                            json.getInt("submission_id"), json.getInt("section_id"),
                                            json.getString("section"),
                                            json.getString("title"), json.getString("doi"),
                                            json.getString("abstract"), json.getString("date_published"),
                                            json.getString("seq"), json.getString("galeys"));
                                    JSONArray jsonArray = json.getJSONArray("keywords");
                                    JSONObject jsonObject;
                                    String string = "";
                                    for (int indx = 0; indx < jsonArray.length(); indx++) {
                                        jsonObject = jsonArray.getJSONObject(indx);
                                        string += jsonObject.getString("keyword") + ", ";
                                    }
                                    if (string.length() > 0)
                                        string = string.substring(0, string.length() - 2);
                                    publication.setKeywords(string);
                                    string = "";
                                    jsonArray = json.getJSONArray("authors");
                                    for (int indx = 0; indx < jsonArray.length(); indx++) {
                                        jsonObject = jsonArray.getJSONObject(indx);
                                        string += jsonObject.getString("nombres") + ", ";
                                    }
                                    if (string.length() > 0)
                                        string = string.substring(0, string.length() - 2);
                                    publication.setAutors(string);

                                    PublicationHolder publicationHolder = new PublicationHolder(PubActivity.this, publication);
                                    placePublication.addView(publicationHolder);
                                } catch (JSONException ex) {
                                    Publication publication = new Publication(0, 0, 0,
                                            "No hay resultados", "No hay resultados",
                                            "No hay resultados", "No hay resultados",
                                            "No hay resultados", "No hay resultados",
                                            "No hay resultados");
                                    PublicationHolder publicationHolder = new PublicationHolder(PubActivity.this, publication);
                                    placePublication.addView(publicationHolder);
                                    System.out.println(ex.toString());
                                }
                            }
                        } else {
                            Publication publication = new Publication(0, 0, 0,
                                    "No hay resultados", "No hay resultados",
                                    "No hay resultados", "No hay resultados",
                                    "No hay resultados", "No hay resultados",
                                    "No hay resultados");
                            PublicationHolder publicationHolder = new PublicationHolder(PubActivity.this, publication);
                            placePublication.addView(publicationHolder);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError ex) {
                Publication publication = new Publication(0, 0, 0,
                        "No hay resultados", "No hay resultados",
                        "No hay resultados", "No hay resultados",
                        "No hay resultados", "No hay resultados",
                        "No hay resultados");
                PublicationHolder publicationHolder = new PublicationHolder(PubActivity.this, publication);
                placePublication.addView(publicationHolder);
                System.out.println(ex.toString());
            }
        });
        requestQueue.add(jsonRequest);
    }

   /* void json2List(, List){


    }*/
}