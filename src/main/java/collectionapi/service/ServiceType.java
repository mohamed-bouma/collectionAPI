package collectionapi.service;


import collectionapi.dao.DAOfactory;
import collectionapi.metier.Type;

public class ServiceType {

    public boolean deleteType(Type type) {
        return DAOfactory.getTypeDAO().delete(type);
    }


    public boolean updateType(Type type) {
        return DAOfactory.getTypeDAO().update(type);
    }


    public boolean insertType(Type type) {
        return DAOfactory.getTypeDAO().insert(type);
    }


}
