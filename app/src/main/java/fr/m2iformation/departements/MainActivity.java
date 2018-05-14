package fr.m2iformation.departements;

import android.content.ContentValues;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import fr.m2iformation.departements.db.DbInit;
import fr.m2iformation.departements.db.DbException;
import fr.m2iformation.departements.model.Departement;

import static fr.m2iformation.departements.tools.Tools.*;

public class MainActivity extends AppCompatActivity {
    private EditText txtSearch;
    private EditText txtNoDept;
    private EditText txtNoRegion;
    private EditText txtNomRegion;
    private EditText txtNom;
    private EditText txtNomStd;
    private EditText txtSurface;
    private EditText txtDateCreation;
    private EditText txtChefLieu;
    private EditText txtUrlWiki;

    private Departement dept;

    private ImageView ivSave,ivDelete,ivAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSearch = findViewById(R.id.txtSearch);
        txtNoDept = findViewById(R.id.txtNoDept);
        txtNoRegion = findViewById(R.id.txtNoRegion);
        txtNomRegion = findViewById(R.id.txtNomRegion);
        txtNom = findViewById(R.id.txtNom);
        txtNomStd = findViewById(R.id.txtNomStd);
        txtSurface = findViewById(R.id.txtSurface);
        txtDateCreation = findViewById(R.id.txtDateCreation);
        txtChefLieu = findViewById(R.id.txtChefLieu);
        txtUrlWiki = findViewById(R.id.txtUrlWiki);

        ivSave = findViewById(R.id.ivSave);
        ivDelete = findViewById(R.id.ivDelete);
        ivAdd = findViewById(R.id.ivAdd);

        dept = new Departement(this);

        reset();
    }

    public void btnSearch(View view) {

        dept.clear();
        String search = txtSearch.getText().toString();
        try {
            dept.select(search);
                setFields();
        }catch( DbException e){
            showToast(this, e.getMessage());
            reset();
        }catch( SQLException e){
            showToast(this, "Erreur de BDD !");
            reset();
        }

        hideKeyboard();
    }

    public void btnDelete(View view) {

        try{
            if(!dept.getNoDept().isEmpty()) {

                dept.delete(dept.getNoDept());
                showToast(this, "Département supprimé !");
            }else{
                showToast(this,"Un champ est vide");
            }
        }catch (SQLException  | NumberFormatException e) {
            e.printStackTrace();

            showToast(this, "Impossible de supprimer le département !");
        }
        reset();
    }

    public void btnSave(View view) {

        try{
            fillDepartement();
            dept.update(dept.getNoDept(),getContentValues());
            showToast(this,"Département modifié !");
            reset();
        }catch (SQLException  | NumberFormatException e){
            e.printStackTrace();
            showToast(this,"Impossible de modifier le département !");
            reset();
        } catch (DbException e) {
            e.printStackTrace();
            showToast(this, e.getMessage());
        }
    }


    public void btnAdd(View view) {

        try {
            fillDepartement();
            dept.insert(getContentValues());
            showToast(this, "Département ajouté !");
            reset();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();

            showToast(this, "Impossible d'ajouter le département !");
            reset();
        } catch (DbException e) {
            e.printStackTrace();
            showToast(this, e.getMessage());
        }
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        String noDept = txtNoDept.getText().toString();
        String noRegion = txtNoRegion.getText().toString();
        String nom = txtNom.getText().toString();
        String nomStd = txtNomStd.getText().toString();
        String surface = txtSurface.getText().toString();
        String dateCreation = txtDateCreation.getText().toString();
        String chefLieu = txtChefLieu.getText().toString();
        String urlWiki = txtUrlWiki.getText().toString();

        cv.put(DbInit.DB_DEPT_FIELDS[0],noDept);
        cv.put(DbInit.DB_DEPT_FIELDS[1],noRegion);
        cv.put(DbInit.DB_DEPT_FIELDS[2],nom);
        cv.put(DbInit.DB_DEPT_FIELDS[3],nomStd);
        cv.put(DbInit.DB_DEPT_FIELDS[4],surface);
        cv.put(DbInit.DB_DEPT_FIELDS[5],dateCreation);
        cv.put(DbInit.DB_DEPT_FIELDS[6],chefLieu);
        cv.put(DbInit.DB_DEPT_FIELDS[7],urlWiki);

        return cv;
    }

    public void fillDepartement() throws NumberFormatException{
        String noDept = txtNoDept.getText().toString();
        String noRegion = txtNoRegion.getText().toString();
        String nom = txtNom.getText().toString();
        String nomStd = txtNomStd.getText().toString();
        String surface = txtSurface.getText().toString();
        String dateCreation = txtDateCreation.getText().toString();
        String chefLieu = txtChefLieu.getText().toString();
        String urlWiki = txtUrlWiki.getText().toString();

        if(isNumeric(noRegion) && isNumeric(surface)) {
            dept.setNoDept(noDept);
            dept.setNoRegion(Integer.parseInt(noRegion));
            dept.setNom(nom);
            dept.setNomStd(nomStd);
            dept.setSurface(Integer.parseInt(surface));
            dept.setDateCreation(dateCreation);
            dept.setChefLieu(chefLieu);
            dept.setUrlWiki(urlWiki);
        }
    }

    public void setFields(){

        txtNoDept.setText(dept.getNoDept());
        txtNoRegion.setText(String.valueOf(dept.getNoRegion()));
        txtNomRegion.setText(String.valueOf(dept.getRegion().getNom()));
        txtNom.setText(dept.getNom());
        txtNomStd.setText(dept.getNomStd());
        txtSurface.setText(String.valueOf(dept.getSurface()));
        txtDateCreation.setText(dept.getDateCreation());
        txtChefLieu.setText(dept.getChefLieu());
        txtUrlWiki.setText(dept.getUrlWiki());

    }

    public void hideKeyboard(){

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm != null && imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void reset(){

        txtSearch.setText("");
        txtNoDept.setText("");
        txtNoRegion.setText("");
        txtNomRegion.setText("");
        txtNom.setText("");
        txtNomStd.setText("");
        txtSurface.setText("");
        txtDateCreation.setText("");
        txtChefLieu.setText("");
        txtUrlWiki.setText("");

        dept.clear();
       /* ivSave.setEnabled(false);
        ivDelete.setEnabled(false);
        ivAdd.setEnabled(true);
    */
        hideKeyboard();
    }

    public void dispEmpty(){
        showToast(this,"Un champ est vide!");

    }



}
