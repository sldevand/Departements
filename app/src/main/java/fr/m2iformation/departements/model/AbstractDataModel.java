package fr.m2iformation.departements.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.m2iformation.departements.db.DbInit;

public abstract class AbstractDataModel {


    protected SQLiteDatabase db;
    protected Context ctx;

    public AbstractDataModel(Context ctx) {

        db = DbInit.getInstance(ctx).getWritableDatabase();
        this.ctx=ctx;
    }
}
