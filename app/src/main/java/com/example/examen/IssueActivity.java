package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

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
import controller.JournalHolder;
import model.Issue;
import model.Journal;

public class IssueActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    PlaceHolderView placeIssue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        requestQueue = Volley.newRequestQueue(this);

        placeIssue = findViewById(R.id.placeIssue);

        Bundle bundle = getIntent().getExtras();
        Journal journal = (Journal) bundle.getSerializable("journal");
        traerVolumenesRevistas(String.valueOf(journal.getJournal_id()));
    }

    private void traerVolumenesRevistas(String idJournal) {
        String url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + idJournal;
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int tamanio = response.length();
                        if (tamanio > 0) {
                            for (int i = 0; i < tamanio; i++) {
                                try {
                                    JSONObject json = new JSONObject(response.get(i).toString());
                                    Issue issue = new Issue(json.getInt("issue_id"),
                                            json.getInt("volume"), json.getInt("number"),
                                            json.getInt("year"), json.getString("date_published"),
                                            json.getString("title"), json.getString("doi"),
                                            json.getString("cover"));
                                    IssueHolder issueHolder = new IssueHolder(IssueActivity.this, issue);
                                    placeIssue.addView(issueHolder);

                                } catch (JSONException ex) {
                                    Issue issue = new Issue(0, 0, 0, 0,
                                            "No hay resultados", "No hay resultados",
                                            "No hay resultados", "https://cdn3.josefacchin.com/wp-content/uploads/2018/09/http-not-found-error-404.png");
                                    IssueHolder issueHolder = new IssueHolder(IssueActivity.this, issue);
                                    placeIssue.addView(issueHolder);
                                    System.out.println(ex.toString());
                                }
                            }
                        } else {
                            Issue issue = new Issue(0, 0, 0, 0,
                                    "No hay resultados", "No hay resultados",
                                    "No hay resultados", "https://cdn3.josefacchin.com/wp-content/uploads/2018/09/http-not-found-error-404.png");
                            IssueHolder issueHolder = new IssueHolder(IssueActivity.this, issue);
                            placeIssue.addView(issueHolder);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError ex) {
                Issue issue = new Issue(0, 0, 0, 0,
                        "No hay resultados", "No hay resultados",
                        "No hay resultados", "https://cdn3.josefacchin.com/wp-content/uploads/2018/09/http-not-found-error-404.png");
                IssueHolder issueHolder = new IssueHolder(IssueActivity.this, issue);
                placeIssue.addView(issueHolder);
                System.out.println(ex.toString());
            }
        });
        requestQueue.add(jsonRequest);
    }

}