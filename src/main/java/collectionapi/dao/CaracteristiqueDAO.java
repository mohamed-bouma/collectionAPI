package collectionapi.dao;

import collectionapi.metier.Caracteristique;
import collectionapi.metier.Referenciel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CaracteristiqueDAO extends DAO<Caracteristique,Caracteristique> {

    private static final Connection connexion = CollectionConnect.getInstance();

    public CaracteristiqueDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Caracteristique getByID(int id) {
        return null;
    }


    public static ArrayList<Referenciel> getByIdCaracteristique(int id){
        ArrayList<Referenciel> listeReferentiel = new ArrayList<>();
        Boolean enMemoire=false;
        if(!Referenciel.getListeReferenciels().isEmpty()){
            for(int i=0;i<Referenciel.getListeReferenciels().size();i++){
                if(Referenciel.getListeReferenciels().get(i).getId_caracteristique()==id){
                    listeReferentiel.add(Referenciel.getListeReferenciels().get(i));
                    enMemoire=true;
                }
            }
        }
        if(!enMemoire) {
            try {

                PreparedStatement pStmt = connexion
                        .prepareStatement("select * from referenciel where id_caracteristique =  " + id);
                // Determine the column set column

//            pStmt.setInt(1, );
                ResultSet rs;
                rs = pStmt.executeQuery();

                while (rs.next()) {
                    Referenciel newReferentiel = new Referenciel();
                    newReferentiel.setId_referenciel(rs.getInt(1));
                    newReferentiel.setLibelle_referenciel(rs.getString(2));
                    newReferentiel.setId_caracteristique(rs.getInt(3));
                    listeReferentiel.add(newReferentiel);
                }
                rs.close();
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listeReferentiel;

    }

    @Override
    public ArrayList<Caracteristique> getAll() {

        ResultSet rs;
        String Statement = "SELECT * FROM caracteristique";
        ArrayList<Caracteristique> listeCaracteristique= new ArrayList<Caracteristique>();
        int i=0;

        try (Statement cStmt = connexion.createStatement()) {

            rs = cStmt.executeQuery(Statement);
            //  rs = cStmt.getResultSet();

            while (rs.next()) {
                listeCaracteristique.add(new Caracteristique());
                listeCaracteristique.get(i).setId_caracteristique(rs.getInt(1));
                listeCaracteristique.get(i++).setLibelle_caracteristique(rs.getString(2));

            }

            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listeCaracteristique;
    }


    @Override
    public ArrayList<Caracteristique> getLike(Caracteristique objet) {
        return null;
    }

    @Override
    public boolean insert(Caracteristique objet) {
            return false;
    }

    @Override
    public boolean update(Caracteristique object) {
        return false;
    }

    @Override
    public boolean delete(Caracteristique object) {
        return false;
    }


}