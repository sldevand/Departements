package fr.m2iformation.departements.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.Objects;
import java.util.regex.Pattern;

import fr.m2iformation.departements.db.DbException;
import fr.m2iformation.departements.db.DbInit;

public class Departement extends AbstractDataModel {

    private String noDept;
    private int noRegion;
    private String nom;
    private String nomStd;
    private int surface;
    private String dateCreation;
    private String chefLieu;
    private String urlWiki;
    private Region region;

    public Departement(Context ctx) {
        super(ctx);
    }

    public void select(String no) throws SQLException, DbException {

        if (!no.equals("")) {
            Cursor cursor = db.query(DbInit.DB_DEPT_TABLE_NAME, DbInit.DB_DEPT_FIELDS, DbInit.DB_DEPT_FIELDS[0] + " = ?", new String[]{no}, null, null, null);

            if (cursor.moveToFirst()) {
                noDept = cursor.getString(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[0]));
                noRegion = cursor.getInt(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[1]));
                nom = cursor.getString(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[2]));
                nomStd = cursor.getString(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[3]));
                surface = cursor.getInt(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[4]));
                dateCreation = cursor.getString(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[5]));
                chefLieu = cursor.getString(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[6]));
                urlWiki = cursor.getString(cursor.getColumnIndex(DbInit.DB_DEPT_FIELDS[7]));
                region = new Region(ctx, noRegion);

            } else {
                cursor.close();
                throw new DbException("Aucun département Trouvé !");
            }
        }
    }

    public void delete(String no) throws SQLException {
        db.delete(DbInit.DB_DEPT_TABLE_NAME, DbInit.DB_DEPT_FIELDS[0] + " = ?", new String[]{no});
    }

    public void update(String no, ContentValues cv) throws SQLException, DbException {
        isValid();
        db.update(DbInit.DB_DEPT_TABLE_NAME, cv, DbInit.DB_DEPT_FIELDS[0] + " = ?", new String[]{no});


    }

    public void insert(ContentValues cv) throws SQLException, DbException {
        isValid();
        db.insert(DbInit.DB_DEPT_TABLE_NAME, null, cv);
    }

    public void clear() {
        noDept = "";
        noRegion = 0;
        if (region != null) {
            region.setNom("");
            region.setNoRegion(0);
        }
        nom = "";
        nomStd = "";
        surface = 0;
        dateCreation = "";
        chefLieu = "";
        urlWiki = "";
    }

    private void isValid() throws DbException {
        noDept=noDept.trim();
        String regex = "([0-9]{2}|2A|2B|97[0-5])";
        boolean match = Pattern.matches(regex, noDept);

        if (!match) {
            throw new DbException("Numero de Departement invalide");
        }
    }

    public String getNoDept() {
        return noDept;
    }

    public void setNoDept(String noDept) {
        this.noDept = noDept;
    }

    public int getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomStd() {
        return nomStd;
    }

    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String getUrlWiki() {
        return urlWiki;
    }

    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return noRegion == that.noRegion &&
                Objects.equals(noDept, that.noDept) &&
                Objects.equals(nomStd, that.nomStd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(noDept, noRegion, nomStd);
    }

    @Override
    public String toString() {
        return "Departement{" +
                "noDept='" + noDept + '\'' +
                ", noRegion=" + noRegion +
                ", nom='" + nom + '\'' +
                ", nomStd='" + nomStd + '\'' +
                ", surface=" + surface +
                ", dateCreation='" + dateCreation + '\'' +
                ", chefLieu='" + chefLieu + '\'' +
                ", urlWiki='" + urlWiki + '\'' +
                '}';
    }
}
