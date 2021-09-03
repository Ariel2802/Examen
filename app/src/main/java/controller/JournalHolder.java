package controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examen.IssueActivity;
import com.example.examen.R;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import model.Journal;

@Animate(Animate.CARD_LEFT_IN_ASC)
@NonReusable
@Layout(R.layout.lyt_journals)
public class JournalHolder {
    private Journal journal;
    private Context context;

    @View(R.id.placeJournal)
    PlaceHolderView placeHolderView;

    @View(R.id.picPortada)
    ImageView picPortada;

    @View(R.id.lblName)
    TextView lblName;
    @View(R.id.lblDescription)
    TextView lblDescription;
    @View(R.id.lblAbreviation)
    TextView lblAbreviation;

    public JournalHolder(Context context, Journal journal) {
        this.context = context;
        this.journal = journal;
    }

    @Click(R.id.relativeJournal)
    public void eventoClic() {
        Intent intent = new Intent(context, IssueActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("journal", journal);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Resolve
    public void onResolved() {
        Glide.with(context).load(journal.getPortada()).into(picPortada);
        lblName.setText(journal.getName());
        lblDescription.setText(Html.fromHtml(journal.getDescription()));
        lblAbreviation.setText("Abreviation: " + String.valueOf(journal.getAbbreviation()));
    }
}
