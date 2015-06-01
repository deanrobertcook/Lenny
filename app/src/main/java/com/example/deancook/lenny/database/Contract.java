package com.example.deancook.lenny.database;

import android.provider.BaseColumns;

/**
 * Created by deancook on 01/06/15.
 */
public final class Contract {
    public Contract() {}

    public static abstract class Airline implements BaseColumns {
        public static final String TABLE_NAME = "Airline";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SITE = "site";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_LOGOURL = "logo_url";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_NAME_CODE + " TEXT, " +
                    COLUMN_NAME_NAME + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME_SITE + " TEXT, " +
                    COLUMN_NAME_PHONE + " TEXT, " +
                    COLUMN_NAME_LOGOURL + " TEXT" +
                ")";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
