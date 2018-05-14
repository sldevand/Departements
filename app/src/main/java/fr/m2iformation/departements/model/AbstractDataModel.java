package fr.m2iformation.departements.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.m2iformation.departements.db.DbInit;

public abstract class AbstractDataModel {

    protected DbInit dbInit;
    protected SQLiteDatabase db;
    protected Context ctx;

    public AbstractDataModel(Context ctx) {

        this.dbInit = DbInit.getInstance(ctx);
        db = dbInit.getWritableDatabase();
        this.ctx=ctx;
    }
}
