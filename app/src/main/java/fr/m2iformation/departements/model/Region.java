package fr.m2iformation.departements.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.Objects;
import java.util.regex.Pattern;

import fr.m2iformation.departements.db.DbException;
import fr.m2iformation.departements.db.DbInit;

public class Region extends AbstractDataModel{

    private Integer noRegion;
    private String nom;

    public Region(Context ctx) {
        super(ctx);
    }

    public Region(Context ctx,Integer no) throws DbException {
        this(ctx);
        select(no);
    }


    public void select(Integer no) throws SQLException, DbException {

        if(no > 0) {
            Cursor cursor = db.query(DbInit.DB_REGION_TABLE_NAME, DbInit.DB_REGION_FIELDS, DbInit.DB_REGION_FIELDS[0] + " = ?", new String[]{no.toString()}, null, null, null);

            if (cursor.moveToFirst()) {
                noRegion = cursor.getInt(cursor.getColumnIndex(DbInit.DB_REGION_FIELDS[0]));
                nom = cursor.getString(cursor.getColumnIndex(DbInit.DB_REGION_FIELDS[1]));

            } else {
                cursor.close();
                throw new DbException("Aucune Région Trouvée !");
            }
        }
    }

    public void delete(String no) throws SQLException{
        db.delete(DbInit.DB_REGION_TABLE_NAME, DbInit.DB_REGION_FIELDS[0]+" = ?", new String[]{no});
    }

    public void update(String no,ContentValues cv) throws SQLException, DbException {
        isValid();
        db.update(DbInit.DB_REGION_TABLE_NAME, cv, DbInit.DB_REGION_FIELDS[0] + " = ?", new String[]{no});


    }

    public void insert(ContentValues cv) throws SQLException, DbException {
        isValid();
        db.insert(DbInit.DB_REGION_TABLE_NAME, null, cv);
    }

    public void clear(){
        noRegion = 0;
        nom = "";
    }

    public void isValid() throws DbException {

        String regex = "([0-9]{2})";
        boolean match = Pattern.matches(regex,noRegion.toString());

        if(!match){
            throw new DbException("Numero de Region invalide");
        }
    }

    public Integer getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(Integer noRegion) {
        this.noRegion = noRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(noRegion, region.noRegion) &&
                Objects.equals(nom, region.nom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(noRegion, nom);
    }

    @Override
    public String toString() {
        return "Region{" +
                "noRegion=" + noRegion +
                ", nom='" + nom + '\'' +
                '}';
    }
}
