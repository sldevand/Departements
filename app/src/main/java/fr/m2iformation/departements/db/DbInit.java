package fr.m2iformation.departements.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbInit extends SQLiteOpenHelper {

    public static String DB_REGION_TABLE_NAME = "regions";
    public static String[] DB_REGION_FIELDS = new String[]{"no_region", "nom"};
    public static String DB_DEPT_TABLE_NAME = "departements";
    public static String[] DB_DEPT_FIELDS = new String[]{"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"};
    private static String DB_NAME = "geo";
    private static DbInit instance;

    private DbInit(Context ctxt) {
        super(ctxt, DB_NAME, null, 1);

    }

    public static DbInit getInstance(Context ctxt) {
        if (instance == null) {
            instance = new DbInit(ctxt);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createRegionTable(db);
        createDepartementTable(db);
        insertAllRegions(db);
        insertAllDepartements(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createRegionTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE regions (" +
                "   no_region INTEGER PRIMARY KEY NOT NULL" +
                ",  nom TEXT NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    private void createDepartementTable(SQLiteDatabase db) {


        String sql = "CREATE TABLE departements (" +
                "   no_dept TEXT PRIMARY KEY NOT NULL" +
                ",  no_region INTEGER" +
                ",  nom TEXT NOT NULL" +
                ",  nom_std TEXT NOT NULL" +
                ",  surface INTEGER" +
                ",  date_creation TEXT" +
                ",  chef_lieu TEXT" +
                ",  url_wiki TEXT" +
                ",  FOREIGN KEY(no_region) REFERENCES Regions(no_region)" +
                ")";
        db.execSQL(sql);
    }

    private void insertRegion(SQLiteDatabase db, String noRegion, String nom) {

        db.execSQL("INSERT INTO Regions (no_region, nom) VALUES (" + noRegion + ",'" + nom + "')");
    }

    private void insertDept(SQLiteDatabase db, String noDept, String noRegion, String nom,
                            String nomStd, String surface, String dateCreation,
                            String chefLieu, String urlWiki) {

        String sql = "INSERT INTO departements " +
                "(no_dept,no_region,nom,nom_std,surface,date_creation,chef_lieu,url_wiki) " +
                "values ('" +
                noDept + "','" + noRegion + "','" + nom + "','" + nomStd + "','" + surface + "','" +
                dateCreation + "','" + chefLieu + "','" + urlWiki + "')";

        db.execSQL(sql);
    }

    private void insertAllRegions(SQLiteDatabase db) {
        insertRegion(db, "11", "Île-de-France");
        insertRegion(db, "21", "Champagne-Ardenne");
        insertRegion(db, "22", "Picardie");
        insertRegion(db, "23", "Haute-Normandie");
        insertRegion(db, "24", "Centre");
        insertRegion(db, "25", "Basse-Normandie");
        insertRegion(db, "26", "Bourgogne");
        insertRegion(db, "31", "Nord-Pas-de-Calais");
        insertRegion(db, "41", "Lorraine");
        insertRegion(db, "42", "Alsace");
        insertRegion(db, "43", "Franche-Comté");
        insertRegion(db, "52", "Pays de la Loire");
        insertRegion(db, "53", "Bretagne");
        insertRegion(db, "54", "Poitou-Charentes");
        insertRegion(db, "72", "Aquitaine");
        insertRegion(db, "73", "Midi-Pyrénées");
        insertRegion(db, "74", "Limousin");
        insertRegion(db, "82", "Rhône-Alpes");
        insertRegion(db, "83", "Auvergne");
        insertRegion(db, "91", "Languedoc-Roussillon");
        insertRegion(db, "93", "Provence-Alpes-Côte d''Azur");
        insertRegion(db, "94", "Corse");
        insertRegion(db, "97", "DOM");

    }

    private void insertAllDepartements(SQLiteDatabase db) {

        insertDept(db, "01", "82", "Ain", "AIN", "5762", "1790-03-04T12:00:00", "Bourg-en-Bresse", "http://fr.wikipedia.org/wiki/Ain_(d%C3%A9partement)");
        insertDept(db, "02", "22", "Aisne", "AISNE", "7369", "1790-03-04T12:00:00", "Laon", "http://fr.wikipedia.org/wiki/Aisne_(d%C3%A9partement)");
        insertDept(db, "03", "83", "Allier ", "ALLIER", "7340", "1790-03-04T12:00:00", "Moulins", "http://fr.wikipedia.org/wiki/Allier_(d%C3%A9partement)");
        insertDept(db, "04", "93", "Alpes-de-Haute-Provence", "ALPES DE HAUTE PROVENCE", "6925", "1790-03-04T12:00:00", "Digne", "http://fr.wikipedia.org/wiki/Alpes-de-Haute-Provence");
        insertDept(db, "05", "93", "Alpes (Hautes)", "HAUTES ALPES", "5549", "1790-03-04T12:00:00", "Gap", "http://fr.wikipedia.org/wiki/Hautes-Alpes");
        insertDept(db, "06", "93", "Alpes-Maritimes", "ALPES MARITIMES", "4299", "1860-06-14T12:00:00", "Nice", "http://fr.wikipedia.org/wiki/Alpes-Maritimes");
        insertDept(db, "07", "82", "Ardéche", "ARDECHE", "5529", "1790-03-04T12:00:00", "Privas", "http://fr.wikipedia.org/wiki/Ard%C3%A8che_(d%C3%A9partement)");
        insertDept(db, "08", "21", "Ardennes", "ARDENNES", "5229", "1790-03-04T12:00:00", "Charleville-Mézières", "http://fr.wikipedia.org/wiki/Ardennes_(d%C3%A9partement)");
        insertDept(db, "09", "73", "Ariége", "ARIEGE", "4890", "1790-03-04T12:00:00", "Foix", "http://fr.wikipedia.org/wiki/Ari%C3%A8ge_(d%C3%A9partement)");
        insertDept(db, "10", "21", "Aube", "AUBE", "6004", "1790-03-04T12:00:00", "Troyes", "http://fr.wikipedia.org/wiki/Aube_(d%C3%A9partement)");
        insertDept(db, "11", "91", "Aude", "AUDE", "6139", "1790-03-04T12:00:00", "Carcassonne", "http://fr.wikipedia.org/wiki/Aude_(d%C3%A9partement)");
        insertDept(db, "12", "73", "Aveyron", "AVEYRON", "8735", "1790-03-04T12:00:00", "Rodez", "http://fr.wikipedia.org/wiki/Aveyron_(d%C3%A9partement)");
        insertDept(db, "13", "93", "Bouches-du-Rhône", "BOUCHES DU RHONE", "5087", "1790-03-04T12:00:00", "Marseille", "http://fr.wikipedia.org/wiki/Bouches-du-Rh%C3%B4ne");
        insertDept(db, "14", "25", "Calvados", "CALVADOS", "5548", "1790-03-04T12:00:00", "Caen", "http://fr.wikipedia.org/wiki/Calvados_(d%C3%A9partement)");
        insertDept(db, "15", "83", "Cantal", "CANTAL", "5726", "1790-03-04T12:00:00", "Aurillac", "http://fr.wikipedia.org/wiki/Cantal_%28d%C3%A9partement%29");
        insertDept(db, "16", "54", "Charente", "CHARENTE", "5956", "1790-03-04T12:00:00", "Angoulême", "http://fr.wikipedia.org/wiki/Charente_(d%C3%A9partement)");
        insertDept(db, "17", "54", "Charente-Maritime", "CHARENTE MARITIME", "6864", "1790-03-04T12:00:00", "La Rochelle", "http://fr.wikipedia.org/wiki/Charente-Maritime");
        insertDept(db, "18", "24", "Cher", "CHER", "7235", "1790-03-04T12:00:00", "Bourges", "http://fr.wikipedia.org/wiki/Cher_(d%C3%A9partement)");
        insertDept(db, "19", "74", "Corréze", "CORREZE", "5857", "1790-03-04T12:00:00", "Tulle", "http://fr.wikipedia.org/wiki/Corr%C3%A8ze_(d%C3%A9partement)");
        insertDept(db, "21", "26", "Côte d''or", "COTE D''OR", "8763", "1790-03-04T12:00:00", "Dijon", "http://fr.wikipedia.org/wiki/C%C3%B4te-d%27Or");
        insertDept(db, "22", "53", "Côtes d''armor", "COTES D''ARMOR", "6878", "1790-03-04T12:00:00", "St-Brieuc", "http://fr.wikipedia.org/wiki/C%C3%B4tes-d%27Armor");
        insertDept(db, "2A", "94", "Corse du sud", "CORSE DU SUD", "4014", "1976-01-01T12:00:00", "Ajaccio", "http://fr.wikipedia.org/wiki/Corse-du-Sud");
        insertDept(db, "2B", "94", "Haute corse", "HAUTE CORSE", "4666", "1976-01-01T12:00:00", "Bastia", "http://fr.wikipedia.org/wiki/Haute-Corse");
        insertDept(db, "23", "74", "Creuse", "CREUSE", "5565", "1790-03-04T12:00:00", "Guéret", "http://fr.wikipedia.org/wiki/Creuse_(d%C3%A9partement)");
        insertDept(db, "24", "72", "Dordogne", "DORDOGNE", "9060", "1790-03-04T12:00:00", "Périgueux", "http://fr.wikipedia.org/wiki/Dordogne_(d%C3%A9partement)");
        insertDept(db, "25", "43", "Doubs", "DOUBS", "5234", "1790-03-04T12:00:00", "Besançon", "http://fr.wikipedia.org/wiki/Doubs_(d%C3%A9partement)");
        insertDept(db, "26", "82", "Drôme", "DROME", "6530", "1790-03-04T12:00:00", "Valence", "http://fr.wikipedia.org/wiki/Dr%C3%B4me_(d%C3%A9partement)");
        insertDept(db, "27", "23", "Eure", "EURE", "6040", "1790-03-04T12:00:00", "Evreux", "http://fr.wikipedia.org/wiki/Eure_(d%C3%A9partement)");
        insertDept(db, "28", "24", "Eure et Loir", "EURE ET LOIR", "5880", "1790-03-04T12:00:00", "Chartres", "http://fr.wikipedia.org/wiki/Eure-et-Loir");
        insertDept(db, "29", "53", "Finistére", "FINISTERE", "6733", "1790-03-04T12:00:00", "Quimper", "http://fr.wikipedia.org/wiki/Finist%C3%A8re");
        insertDept(db, "30", "91", "Gard", "GARD", "5853", "1790-03-04T12:00:00", "Nîmes", "http://fr.wikipedia.org/wiki/Gard");
        insertDept(db, "31", "73", "Garonne (Haute)", "HAUTE GARONNE", "6309", "1790-03-04T12:00:00", "Toulouse", "http://fr.wikipedia.org/wiki/Haute-Garonne");
        insertDept(db, "32", "73", "Gers", "GERS", "6257", "1790-03-04T12:00:00", "Auch", "http://fr.wikipedia.org/wiki/Gers_(d%C3%A9partement)");
        insertDept(db, "33", "72", "Gironde", "GIRONDE", "10725", "1790-03-04T12:00:00", "Bordeaux", "http://fr.wikipedia.org/wiki/Gironde_(d%C3%A9partement)");
        insertDept(db, "34", "91", "Hérault", "HERAULT", "6224", "1790-03-04T12:00:00", "Montpellier", "http://fr.wikipedia.org/wiki/H%C3%A9rault_(d%C3%A9partement)");
        insertDept(db, "35", "53", "Ile et Vilaine", "ILE ET VILAINE", "6775", "1790-03-04T12:00:00", "Rennes", "http://fr.wikipedia.org/wiki/Ille-et-Vilaine");
        insertDept(db, "36", "24", "Indre", "INDRE", "6903", "1790-03-04T12:00:00", "Châteauroux", "http://fr.wikipedia.org/wiki/Indre_(d%C3%A9partement)");
        insertDept(db, "37", "24", "Indre et Loire", "INDRE ET LOIRE", "6127", "1790-03-04T12:00:00", "Tours", "http://fr.wikipedia.org/wiki/Indre-et-Loire");
        insertDept(db, "38", "82", "Isére", "ISERE", "7431", "1790-03-04T12:00:00", "Grenoble", "http://fr.wikipedia.org/wiki/Is%C3%A8re_(d%C3%A9partement)");
        insertDept(db, "39", "43", "Jura", "JURA", "4999", "1790-03-04T12:00:00", "Lons-le-Saunier", "http://fr.wikipedia.org/wiki/Jura_(d%C3%A9partement)");
        insertDept(db, "40", "72", "Landes", "LANDES", "9243", "1790-03-04T12:00:00", "Mont-de-Marsan", "http://fr.wikipedia.org/wiki/Landes_(d%C3%A9partement)");
        insertDept(db, "41", "24", "Loir et Cher", "LOIR ET CHER", "6343", "1790-03-04T12:00:00", "Blois", "http://fr.wikipedia.org/wiki/Loir-et-Cher");
        insertDept(db, "42", "82", "Loire", "LOIRE", "4781", "1793-08-12T12:00:00", "St-Étienne", "http://fr.wikipedia.org/wiki/Loire_(d%C3%A9partement)");
        insertDept(db, "43", "83", "Loire (Haute)", "HAUTRE LOIRE", "4977", "1790-03-04T12:00:00", "Le Puy", "http://fr.wikipedia.org/wiki/Haute-Loire");
        insertDept(db, "44", "52", "Loire Atlantique", "LOIRE ATLANTIQUE", "6815", "1790-03-04T12:00:00", "Nantes", "http://fr.wikipedia.org/wiki/Loire-Atlantique");
        insertDept(db, "45", "24", "Loiret", "LOIRET", "6775", "1790-03-04T12:00:00", "Orléans", "http://fr.wikipedia.org/wiki/Loiret_(d%C3%A9partement)");
        insertDept(db, "46", "73", "Lot", "LOT", "5217", "1790-03-04T12:00:00", "Cahors", "http://fr.wikipedia.org/wiki/Lot_(d%C3%A9partement)");
        insertDept(db, "47", "72", "Lot et Garonne", "LOT ET GARONNE", "5361", "1790-03-04T12:00:00", "Agen", "http://fr.wikipedia.org/wiki/Lot-et-Garonne");
        insertDept(db, "48", "91", "Lozére", "LOZERE", "5167", "1790-03-04T12:00:00", "Mende", "http://fr.wikipedia.org/wiki/Loz%C3%A8re_(d%C3%A9partement)");
        insertDept(db, "49", "52", "Maine et Loire", "MAINE ET LOIRE", "7166", "1790-03-04T12:00:00", "Angers", "http://fr.wikipedia.org/wiki/Maine-et-Loire");
        insertDept(db, "50", "25", "Manche", "MANCHE", "5938", "1790-03-04T12:00:00", "St-Lô", "http://fr.wikipedia.org/wiki/Manche_(d%C3%A9partement)");
        insertDept(db, "51", "21", "Marne", "MARNE", "8162", "1790-03-04T12:00:00", "Châlons-sur-Marne", "http://fr.wikipedia.org/wiki/Marne_(d%C3%A9partement)");
        insertDept(db, "52", "21", "Marne (Haute)", "HAUTE MARNE", "6211", "1790-03-04T12:00:00", "Chaumont", "http://fr.wikipedia.org/wiki/Haute-Marne");
        insertDept(db, "53", "52", "Mayenne", "MAYENNE", "5175", "1790-03-04T12:00:00", "Laval", "http://fr.wikipedia.org/wiki/Mayenne_(d%C3%A9partement)");
        insertDept(db, "54", "41", "Meurthe et Moselle", "MEURTHE ET MOSELLE", "5246", "1871-09-07T12:00:00", "Nancy", "http://fr.wikipedia.org/wiki/Meurthe-et-Moselle");
        insertDept(db, "55", "41", "Meuse", "MEUSE", "6211", "1790-03-04T12:00:00", "Bar-le-Duc", "http://fr.wikipedia.org/wiki/Meuse_(d%C3%A9partement)");
        insertDept(db, "56", "53", "Morbihan", "MORBIHAN", "6823", "1790-03-04T12:00:00", "Vannes", "http://fr.wikipedia.org/wiki/Morbihan");
        insertDept(db, "57", "41", "Moselle", "MOSELLE", "6216", "1919-06-28T12:00:00", "Metz", "http://fr.wikipedia.org/wiki/Moselle_(d%C3%A9partement)");
        insertDept(db, "58", "26", "Niévre", "NIEVRE", "6817", "1790-03-04T12:00:00", "Nevers", "http://fr.wikipedia.org/wiki/Ni%C3%A8vre_(d%C3%A9partement)");
        insertDept(db, "59", "31", "Nord", "NORD", "5743", "1790-03-04T12:00:00", "Lille", "http://fr.wikipedia.org/wiki/Nord_(d%C3%A9partement)");
        insertDept(db, "60", "22", "Oise", "OISE", "5860", "1790-03-04T12:00:00", "Beauvais", "http://fr.wikipedia.org/wiki/Oise_(d%C3%A9partement)");
        insertDept(db, "61", "25", "Orne", "ORNE", "6103", "1790-03-04T12:00:00", "Alençon", "http://fr.wikipedia.org/wiki/Orne_(d%C3%A9partement)");
        insertDept(db, "62", "31", "Pas de Calais", "PAS DE CALAIS", "6671", "1790-03-04T12:00:00", "Arras", "http://fr.wikipedia.org/wiki/Pas-de-Calais");
        insertDept(db, "63", "83", "Puy de Dôme", "PUY DE DOME", "7970", "1790-03-04T12:00:00", "Clermont-Ferrand", "http://fr.wikipedia.org/wiki/Puy-de-D%C3%B4me");
        insertDept(db, "64", "72", "Pyrénées Atlantiques", "PYRENEES ATLANTIQUES", "7645", "1790-03-04T12:00:00", "Pau", "http://fr.wikipedia.org/wiki/Pyr%C3%A9n%C3%A9es-Atlantiques");
        insertDept(db, "65", "73", "Pyrénées (Hautes)", "HAUTES PYRENEES", "4464", "1790-03-04T12:00:00", "Tarbes", "http://fr.wikipedia.org/wiki/Hautes-Pyr%C3%A9n%C3%A9es");
        insertDept(db, "66", "91", "Pyrénées Orientales", "PYRENEES ORIENTALES", "4116", "1790-03-04T12:00:00", "Perpignan", "http://fr.wikipedia.org/wiki/Pyr%C3%A9n%C3%A9es-Orientales");
        insertDept(db, "67", "42", "Rhin (Bas)", "BAS RHIN", "4755", "1790-03-04T12:00:00", "Strasbourg", "http://fr.wikipedia.org/wiki/Bas-Rhin");
        insertDept(db, "68", "42", "Rhin (Haut)", "HAUT RHIN", "3525", "1790-03-04T12:00:00", "Colmar", "http://fr.wikipedia.org/wiki/Haut-Rhin");
        insertDept(db, "69", "82", "Rhône", "RHONE", "3249", "1793-08-12T12:00:00", "Lyon", "http://fr.wikipedia.org/wiki/Rh%C3%B4ne_(d%C3%A9partement)");
        insertDept(db, "70", "43", "Saône (Haute)", "HAUTE SAONE", "5360", "1790-03-04T12:00:00", "Vesoul", "http://fr.wikipedia.org/wiki/Haute-Sa%C3%B4ne");
        insertDept(db, "71", "26", "Saône et Loire", "SAONE ET LOIRE", "8575", "1790-03-04T12:00:00", "Mâcon", "http://fr.wikipedia.org/wiki/Sa%C3%B4ne-et-Loire");
        insertDept(db, "72", "52", "Sarthe", "SARTHE", "6206", "1790-03-04T12:00:00", "Le Mans", "http://fr.wikipedia.org/wiki/Sarthe_(d%C3%A9partement)");
        insertDept(db, "73", "82", "Savoie", "SAVOIE", "6028", "1860-06-15T12:00:00", "Chambéry", "http://fr.wikipedia.org/wiki/Savoie_(d%C3%A9partement)");
        insertDept(db, "74", "82", "Savoie (Haute)", "HAUTE SAVOIE", "4388", "1860-06-14T12:00:00", "Annecy", "http://fr.wikipedia.org/wiki/Haute-Savoie");
        insertDept(db, "75", "11", "Paris", "PARIS", "105", "", "Paris", "http://fr.wikipedia.org/wiki/Paris");
        insertDept(db, "76", "23", "Seine Maritime", "SEINE MARITIME", "6278", "1790-03-04T12:00:00", "Rouen", "http://fr.wikipedia.org/wiki/Seine-Maritime");
        insertDept(db, "77", "11", "Seine et Marne", "SEINE ET MARNE", "5915", "1790-03-04T12:00:00", "Melun", "http://fr.wikipedia.org/wiki/Seine-et-Marne");
        insertDept(db, "78", "11", "Yvelines", "YVELINES", "2284", "1968-01-01T12:00:00", "Versailles", "http://fr.wikipedia.org/wiki/Yvelines");
        insertDept(db, "79", "54", "Sèvres (Deux)", "DEUX SEVRES", "5999", "1790-03-04T12:00:00", "Niort", "http://fr.wikipedia.org/wiki/Deux-S%C3%A8vres");
        insertDept(db, "80", "22", "Somme", "SOMME", "6170", "1790-03-04T12:00:00", "Amiens", "http://fr.wikipedia.org/wiki/Somme_(d%C3%A9partement)");
        insertDept(db, "81", "73", "Tarn", "TARN", "5758", "1790-03-04T12:00:00", "Albi", "http://fr.wikipedia.org/wiki/Tarn_(d%C3%A9partement)");
        insertDept(db, "82", "73", "Tarn et Garonne", "TARN ET GARONNE", "3718", "1808-11-21T12:00:00", "Montauban", "http://fr.wikipedia.org/wiki/Tarn-et-Garonne");
        insertDept(db, "83", "93", "Var", "VAR", "5973", "1790-03-04T12:00:00", "Toulon", "http://fr.wikipedia.org/wiki/Var_(d%C3%A9partement)");
        insertDept(db, "84", "93", "Vaucluse", "VAUCLUSE", "3567", "1793-08-12T12:00:00", "Avignon", "http://fr.wikipedia.org/wiki/Vaucluse_(d%C3%A9partement)");
        insertDept(db, "85", "52", "Vendée", "VENDEE", "6720", "1790-03-04T12:00:00", "La Roche-sur-Yon", "http://fr.wikipedia.org/wiki/Vend%C3%A9e_(d%C3%A9partement)");
        insertDept(db, "86", "54", "Vienne", "VIENNE", "6990", "1790-03-04T12:00:00", "Poitiers", "http://fr.wikipedia.org/wiki/Vienne_(d%C3%A9partement)");
        insertDept(db, "87", "74", "Vienne (Haute)", "HAUTE VIENNE", "5520", "1790-03-04T12:00:00", "Limoges", "http://fr.wikipedia.org/wiki/Haute-Vienne");
        insertDept(db, "88", "41", "Vosges", "VOSGES", "5874", "1790-03-04T12:00:00", "Épinal", "http://fr.wikipedia.org/wiki/Vosges_(d%C3%A9partement)");
        insertDept(db, "89", "26", "Yonne", "YONNE", "7427", "1790-03-04T12:00:00", "Auxerre", "http://fr.wikipedia.org/wiki/Yonne_(d%C3%A9partement)");
        insertDept(db, "90", "43", "Belfort (Territoire de)", "TERRITOIRE DE BELFORT", "609", "1922-01-01T12:00:00", "Belfort", "http://fr.wikipedia.org/wiki/Territoire_de_Belfort");
        insertDept(db, "91", "11", "Essonne", "ESSONNE", "1804", "1968-01-01T12:00:00", "Evry", "http://fr.wikipedia.org/wiki/Essonne_(d%C3%A9partement)");
        insertDept(db, "92", "11", "Hauts de Seine", "HAUTS DE SEINE", "176", "1968-01-01T12:00:00", "Nanterre", "http://fr.wikipedia.org/wiki/Hauts-de-Seine");
        insertDept(db, "93", "11", "Seine Saint Denis", "SEINE SAINT DENIS", "236", "1968-01-01T12:00:00", "Bobigny", "http://fr.wikipedia.org/wiki/Seine-Saint-Denis");
        insertDept(db, "94", "11", "Val de Marne", "VAL DE MARNE", "245", "1968-01-01T12:00:00", "Créteil", "http://fr.wikipedia.org/wiki/Val-de-Marne");
        insertDept(db, "95", "11", "Val d''oise", "VAL D''OISE", "1246", "1968-01-01T12:00:00", "Pontoise", "http://fr.wikipedia.org/wiki/Val-d%27Oise");
        insertDept(db, "976", "97", "Mayotte", "MAYOTTE", "376", "", "Mamoudzou", "http://fr.wikipedia.org/wiki/Mayotte");
        insertDept(db, "971", "97", "Guadeloupe", "GUADELOUPE", "1628", "", "Basse-Terre", "http://fr.wikipedia.org/wiki/Guadeloupe");
        insertDept(db, "973", "97", "Guyane", "GUYANE", "83846", "", "Cayenne", "http://fr.wikipedia.org/wiki/Guyane");
        insertDept(db, "972", "97", "Martinique", "MARTINIQUE", "1128", "", "Fort-de-France", "http://fr.wikipedia.org/wiki/Martinique");
        insertDept(db, "974", "97", "Réunion", "REUNION", "2512", "", "Saint-Denis", "http://fr.wikipedia.org/wiki/La_R%C3%A9union");

    }

}