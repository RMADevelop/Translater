package com.example.roma.translater.data;


public final class TranslateItem {

    private String wordIn;
    private String wordOut;
    private boolean isFavorite = false;
    private boolean isDelete = false;
    private String langInOut;

    public TranslateItem() {

    }

    public TranslateItem(String wordIn, String wordOut) {
        this.wordIn = wordIn;
        this.wordOut = wordOut;
    }

    public TranslateItem(String wordIn, String wordOut, String langInOut) {
        this.wordIn = wordIn;
        this.wordOut = wordOut;
        this.langInOut = langInOut;
    }

    public String getWordIn() {
        return wordIn;
    }

    public void setWordIn(String wordIn) {
        this.wordIn = wordIn;
    }

    public String getWordOut() {
        return wordOut;
    }

    public void setWordOut(String wordOut) {
        this.wordOut = wordOut;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(int i) {
        if (i == 1)
            isFavorite = true;
        else isFavorite = false;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(int i) {
        if (i==1)
            isDelete = true;
        else isDelete = false;
    }

    public String getLangInOut() {
        return langInOut;
    }

    public void setLangInOut(String langInOut) {
        this.langInOut = langInOut;
    }


}
