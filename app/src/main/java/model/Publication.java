package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Publication implements Serializable {
    int publication_id, submission_id, section_id;
    String section, title, doi, abstractt, date_published, seq, galeys, keywords, autors;

    public Publication() {
    }

    public Publication(int publication_id, int submission_id, int section_id, String section, String title, String doi, String abstractt, String date_published, String seq, String galeys) {
        this.publication_id = publication_id;
        this.submission_id = submission_id;
        this.section_id = section_id;
        this.section = section;
        this.title = title;
        this.doi = doi;
        this.abstractt = abstractt;
        this.date_published = date_published;
        this.seq = seq;
        this.galeys = galeys;
    }

    public Publication(int publication_id, int submission_id, int section_id, String section, String title, String doi, String abstractt, String date_published, String seq, String galeys, String keywords, String autors) {
        this.publication_id = publication_id;
        this.submission_id = submission_id;
        this.section_id = section_id;
        this.section = section;
        this.title = title;
        this.doi = doi;
        this.abstractt = abstractt;
        this.date_published = date_published;
        this.seq = seq;
        this.galeys = galeys;
        this.keywords = keywords;
        this.autors = autors;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setAutors(String autors) {
        this.autors = autors;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(int submission_id) {
        this.submission_id = submission_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getAbstractt() {
        return abstractt;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getGaleys() {
        return galeys;
    }

    public void setGaleys(String galeys) {
        this.galeys = galeys;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getAutors() {
        return autors;
    }
}
