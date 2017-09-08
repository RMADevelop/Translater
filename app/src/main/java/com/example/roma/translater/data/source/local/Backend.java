package com.example.roma.translater.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.roma.translater.data.TranslateItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roma on 15.08.2017.
 */

public class Backend extends TranslateLocalContract.Contract {
    private TranslateLocalDBHelper helper;
    private SQLiteDatabase db;

    public Backend(Context context) {
        helper = new TranslateLocalDBHelper(context);
    }


    public void addItem(TranslateItem item) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TABLE_WORD_IN, item.getWordIn());
            cv.put(TABLE_WORD_OUT, item.getWordOut());
            cv.put(TABLE_LANG_IN_OUT, item.getLangInOut());
            cv.put(TABLE_IS_FAVORITE, item.isFavorite() ? 1 : 0);
            db.insert(TABLE_NAME, null, cv);
            Log.v("DBCheck", "add item" + db.isOpen());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public List<TranslateItem> getAllItems() {
        try {
            db = helper.getReadableDatabase();
            String[] columns = new String[]{TABLE_WORD_IN};

            Cursor cursor = db.query(
                    TABLE_NAME,
                    null,
                    null, null, null, null, null);
            List<TranslateItem> list = new ArrayList<>();
            while (cursor.moveToNext()) {
                list.add(buildItemFromCursor(cursor));
            }
            cursor.close();
            return list;

        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            db.close();
        }
    }

    private TranslateItem buildItemFromCursor(Cursor cursor) {
        TranslateItem item = new TranslateItem();

        item.setWordIn(cursor.getString(cursor.getColumnIndex(TABLE_WORD_IN)));
        item.setWordOut(cursor.getString(cursor.getColumnIndex(TABLE_WORD_OUT)));
        item.setLangInOut(cursor.getString(cursor.getColumnIndex(TABLE_LANG_IN_OUT)));
        Log.v("afdsfadfad", " cursor getInt " + cursor.getColumnIndex(TABLE_IS_FAVORITE));

        item.setFavorite(cursor.getInt(cursor.getColumnIndex(TABLE_IS_FAVORITE)));
        return item;
    }

    public List<TranslateItem> searchHistory(String word) {
        String wordF = "%" + word + "%";
        List<TranslateItem> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        try {

            String[] columns = new String[]{TABLE_WORD_IN, TABLE_WORD_OUT, TABLE_LANG_IN_OUT, TABLE_IS_FAVORITE};
            String where = TABLE_WORD_IN + " LIKE ? OR " + TABLE_WORD_OUT + " LIKE ?";
            String[] whereArgs = new String[]{wordF, wordF};

            Cursor cursor = db.query(
                    TABLE_NAME, columns, where, whereArgs, null, null, null);
            while (cursor.moveToNext()) {
                TranslateItem item = new TranslateItem();
                item.setWordIn(cursor.getString(cursor.getColumnIndex(TABLE_WORD_IN)));
                item.setWordOut(cursor.getString(cursor.getColumnIndex(TABLE_WORD_OUT)));
                item.setLangInOut(cursor.getString(cursor.getColumnIndex(TABLE_LANG_IN_OUT)));
                item.setFavorite(cursor.getInt(cursor.getColumnIndex(TABLE_IS_FAVORITE)));
                list.add(item);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public TranslateItem checkItem(String word) {
        try {
            db = helper.getReadableDatabase();

            String[] columns = new String[]{TABLE_WORD_IN, TABLE_WORD_OUT, TABLE_LANG_IN_OUT, TABLE_IS_FAVORITE};
            String where = TABLE_WORD_IN + " LIKE ? OR " + TABLE_WORD_OUT + " LIKE ?";
            String[] whereArgs = new String[]{word, word};

            Cursor cursor = db.query(
                    TABLE_NAME, columns, where, whereArgs, null, null, null);
            if (cursor.moveToNext()) {
                return buildItemFromCursor(cursor);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return null;
    }

    public List<TranslateItem> getAllFavorite() {
        List<TranslateItem> list = new ArrayList<>();
        try {
            db = helper.getReadableDatabase();
            Log.v("afdsfadfad", " " + db.isOpen());
            String word = "cat";
            String[] columns = new String[]{TABLE_WORD_IN, TABLE_WORD_OUT, TABLE_LANG_IN_OUT, TABLE_IS_FAVORITE};
            String where = TABLE_IS_FAVORITE + "= ?";
            String[] whereArgs = new String[]{String.valueOf(1)};

            Cursor cursor = db.query(
                    TABLE_NAME,
                    columns,
                    where,
                    whereArgs,
                    null, null, null);
            while (cursor.moveToNext()) {
                list.add(buildItemFromCursor(cursor));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public List<TranslateItem> searchFavorite(String word) {
        List<TranslateItem> list = new ArrayList<>();
        try {
            db = helper.getReadableDatabase();

            String[] columns = new String[]{TABLE_WORD_IN, TABLE_WORD_OUT, TABLE_LANG_IN_OUT, TABLE_IS_FAVORITE};
            String where = TABLE_WORD_IN + " LIKE ? OR " + TABLE_WORD_OUT + " LIKE ? AND " + TABLE_IS_FAVORITE + "=?";
            String[] whereArgs = new String[]{word, word, String.valueOf(1)};
            Cursor cursor = db.query(
                    TABLE_NAME,
                    columns,
                    where,
                    whereArgs,
                    null, null, null);
            while (cursor.moveToNext()) {
                list.add(buildItemFromCursor(cursor));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;

    }

    public void setFavorite(TranslateItem item) {
        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TABLE_IS_FAVORITE, item.isFavorite() ? 1 : 0);

        String where = TABLE_WORD_IN + " =? AND " + TABLE_WORD_OUT + " =?";
        String[] whereArgs = new String[]{item.getWordIn(), item.getWordOut()};

        db.update(TABLE_NAME, cv, where, whereArgs);
        Log.v("sdlfskjdfg", "complete");
    }


    public void deleteItems(List<TranslateItem> list) {
        try {
            db = helper.getWritableDatabase();
            String where = TABLE_WORD_IN + " LIKE ? AND " + TABLE_WORD_OUT + " LIKE ?";
            for (TranslateItem item : list) {
                String[] whereArgs = new String[]{item.getWordIn(), item.getWordOut()};

                db.delete(TABLE_NAME, where, whereArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

}
