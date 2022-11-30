package collectionapi.service;


import collectionapi.dao.DAOfactory;
import collectionapi.metier.Produit;
import collectionapi.metier.Type;

import java.util.ArrayList;

public class ServiceProduit {

    private ArrayList<Type> typeFiltrer;


    public ServiceProduit(){
        typeFiltrer = DAOfactory.getTypeDAO().getAll();
    }

    public boolean deleteArticle(Produit article) {
        return DAOfactory.getProduitDAO().delete(article);
    }

    public boolean updateArticle(Produit article) {
        return DAOfactory.getProduitDAO().update(article);
    }


    public boolean insertArticle(Produit article)  {
        return DAOfactory.getProduitDAO().insert(article);
    }


    public ArrayList<Type> getTypeFiltrer() {
        return typeFiltrer;
    }


}
