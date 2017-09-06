package com.example.roma.translater.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by Roma on 10.08.2017.
 */

public class TranslateLocalContract {

    public static abstract class Contract implements BaseColumns {

        public static final String TABLE_NAME = "translateItems";
        public static final String TABLE_ENTRY_ID = "entryid";
        public static final String TABLE_WORD_IN = "translate_word_in";
        public static final String TABLE_WORD_OUT = "translate_word_out";
        public static final String TABLE_LANG_IN_OUT = "translate_lang_in_out";
        public static final String TABLE_IS_FAVORITE = "is_favorite";
        public static final String TABLE_IS_DELETE = "is_delete";

    }
}
