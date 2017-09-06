package com.example.roma.translater;

import java.util.UUID;

/**
 * Created by Roma on 09.08.2017.
 */

public class Item {
    private UUID id;
    private String textIn;
    private String textOut;
    private boolean favorite;
    private int idStateLang;


    public Item(String textIn, String textOut, int idStateLang) {
        this.id = UUID.randomUUID();
        this.textIn = textIn;
        this.textOut = textOut;
        this.idStateLang = idStateLang;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTextIn() {
        return textIn;
    }

    public void setTextIn(String textIn) {
        this.textIn = textIn;
    }

    public String getTextOut() {
        return textOut;
    }

    public void setTextOut(String textOut) {
        this.textOut = textOut;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getIdStateLang() {
        return idStateLang;
    }

    public void setIdStateLang(int idStateLang) {
        this.idStateLang = idStateLang;
    }
}
