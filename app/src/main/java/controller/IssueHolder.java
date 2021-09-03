package controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examen.IssueActivity;
import com.example.examen.PubActivity;
import com.example.examen.R;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import model.Issue;

@Animate(Animate.FADE_IN_ASC)
@NonReusable
@Layout(R.layout.lyt_issue)
public class IssueHolder {
    Issue issue;
    private Context context;

    @View(R.id.placeIssue)
    PlaceHolderView placeIssue;

    @View(R.id.picCover)
    ImageView picCover;

    @View(R.id.lblAutor)
    TextView lblRevista;
    @View(R.id.lblYear)
    TextView lblYear;
    @View(R.id.lblVol)
    TextView lblVol;
    @View(R.id.lblNumero)
    TextView lblNumero;

    public IssueHolder(Context context, Issue issue) {
        this.context = context;
        this.issue = issue;
    }

    @Click(R.id.relativeIssue)
    public void eventoClic() {
        Intent intent = new Intent(context, PubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("issue", issue);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Resolve
    public void onResolved() {
        Glide.with(context).load(issue.getCover()).into(picCover);
        lblRevista.setText(issue.getTitle());
        lblYear.setText("Año: " + String.valueOf(issue.getYear()));
        lblVol.setText("Volumen: " + String.valueOf(issue.getVolume()));
        lblNumero.setText("Número: " + String.valueOf(issue.getNumber()));
    }
}
