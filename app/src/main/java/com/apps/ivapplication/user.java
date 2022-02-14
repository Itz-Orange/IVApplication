package com.apps.ivapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class user {

    public String fullName, email;

    public user() {

    }

    public user(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}

