package com.example.roma.translater.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_ENTRY_ID;
import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_IS_DELETE;
import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_IS_FAVORITE;
import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_LANG_IN_OUT;
import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_NAME;
import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_WORD_IN;
import static com.example.roma.translater.data.source.local.TranslateLocalContract.Contract.TABLE_WORD_OUT;

/**
 * Created by Roma on 12.08.2017.
 */

public class TranslateLocalDBHelper extends SQLiteOpenHelper {
    public final static String DB_NAME = "translate.db";
    private final static int DB_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_DB =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    TABLE_ENTRY_ID + INTEGER_TYPE + " PRIMARY KEY," +
                    TABLE_WORD_IN + TEXT_TYPE + COMMA_SEP +
                    TABLE_WORD_OUT + TEXT_TYPE + COMMA_SEP +
                    TABLE_LANG_IN_OUT + TEXT_TYPE + COMMA_SEP +
                    TABLE_IS_FAVORITE + INTEGER_TYPE+ COMMA_SEP +
                    TABLE_IS_DELETE + INTEGER_TYPE+
                    " )";


    public TranslateLocalDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DB);
        Log.v("DBCheck","db create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
