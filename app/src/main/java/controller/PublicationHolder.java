package controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

import java.util.List;

import model.Journal;
import model.Publication;

@Animate(Animate.CARD_LEFT_IN_ASC)
@NonReusable
@Layout(R.layout.lyt_publication)
public class PublicationHolder {
    Publication publication;
    private Context context;

    @View(R.id.placePublication)
    PlaceHolderView placeHolderView;

    @View(R.id.lblTitulo)
    TextView lblTitulo;
    @View(R.id.lblAutor)
    TextView lblAutor;
    @View(R.id.lblKeyWords)
    TextView lblKeyWords;
    @View(R.id.lblVer)
    TextView lblVer;

    public PublicationHolder(Context context, Publication publication) {
        this.context = context;
        this.publication = publication;
    }

    @Resolve
    public void onResolved() {
        lblTitulo.setText(publication.getTitle());
        String texto = "DOI:\n<a href=\"" + publication.getDoi().replace("\\", "") + "\">" + publication.getDoi() + "</a>";
        lblVer.setMovementMethod(LinkMovementMethod.getInstance());
        lblVer.setText(Html.fromHtml(texto));
        lblAutor.setText("Authors: " + publication.getAutors());
        lblKeyWords.setText("Keywords: " + publication.getKeywords());
    }
}
