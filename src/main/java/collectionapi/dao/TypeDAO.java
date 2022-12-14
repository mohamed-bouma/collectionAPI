package collectionapi.dao;

import collectionapi.metier.Caracteristique;
import collectionapi.metier.Produit;
import collectionapi.metier.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAO extends DAO<Type, Type> {

    private static final Connection connexion = CollectionConnect.getInstance();

    public TypeDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Type getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Type> getAll() {

        ResultSet rs;
        String Statement = "SELECT * FROM TYPE";
        ArrayList<Type> listeTypes= new ArrayList<Type>();


        try (java.sql.Statement cStmt = connexion.createStatement()) {

            rs = cStmt.executeQuery(Statement);
            //  rs = cStmt.getResultSet();

            while (rs.next()) {

                Type type = new Type();
                type.setId(rs.getInt(1));
                type.setLibelle_type(rs.getString(2));
                listeTypes.add(type);

            }

            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listeTypes;
    }

    @Override
    public ArrayList<Type> getLike(Type objet) {
        return null;
    }

    @Override
    public boolean insert(Type type) {

        ResultSet rs;
        String procedureStockee;


        insertType(type);

        for (Object caracteristique : type.getCaracteristiquesType()) {

            String strCarac = caracteristique.toString();

            procedureStockee = "{call dbo.ps_insert_caracteriser (?, ?)}";
            try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
                cStmt.setString(1, type.getLibelle());
                cStmt.setString(2, strCarac);


                cStmt.execute();

            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }



    public static void insertType(Type newType) {
        // return false;
        ResultSet rs;
        String procedureStockee;
        insertType(newType);

        for (Object caracteristique : newType.getCaracteristiquesType()) {
            String strCarac = caracteristique.toString();
            procedureStockee = "{call dbo.ps_insert_type (?)}";
            try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
                cStmt.setString(1, newType.getLibelle());

                cStmt.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean update(Type type) {


        String str = "delete * from caracteriser where id_type = " + type.getId() + " " ;

        for( int i=0 ; i < type.getCaracteristiquesType().size();i++){
            str += " insert into caracteriser values("+type.getId()+", "+type.getCaracteristiquesType().get(i).getId_caracteristique()+")";
        }

        System.out.println("str = "+str);

        try(PreparedStatement cStmt = connexion.prepareStatement(str)){
            return cStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean delete(Type type) {

        try(PreparedStatement cStmt = connexion.prepareStatement("delete from type where id_type = ? "+
                "delete from caracteriser where id_type = ?")){
            cStmt.setInt(1, type.getId());
            cStmt.setInt(2, type.getId());
            return cStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static ArrayList<Caracteristique> getCaracteristiquesTypes(Type type) {
        String procedureStockee;
        ResultSet rs;

        ArrayList<Caracteristique> typecaracList = new ArrayList<>();


        procedureStockee = "{call dbo.ps_get_type_id (?)}";
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, type.getLibelle());

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                type.setId(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        procedureStockee = "{call dbo.get_caracteristiques_type (?)}";
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, type.getLibelle());

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                Caracteristique caracteristique = new Caracteristique();
                caracteristique.setLibelle_caracteristique(rs.getString(1));
                typecaracList.add(caracteristique);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(typecaracList);
        return typecaracList;
    }

}
