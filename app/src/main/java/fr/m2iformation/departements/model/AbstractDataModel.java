package fr.m2iformation.departements.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.m2iformation.departements.db.DbInit;

abstract class AbstractDataModel {


    SQLiteDatabase db;
    Context ctx;

    AbstractDataModel(Context ctx) {

        db = DbInit.getInstance(ctx).getWritableDatabase();
        this.ctx=ctx;
    }
}
