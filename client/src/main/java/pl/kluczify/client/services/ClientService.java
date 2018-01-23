package pl.kluczify.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kluczify.client.dao.ClientDao;
import pl.kluczify.client.dao.PermissionDao;
import pl.kluczify.client.dao.PersistentDao;
import pl.kluczify.client.model.Client;
import pl.kluczify.client.model.Permission;

import java.sql.Date;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PersistentDao persistentDao;

    public Permission addPermissions(long id, Date startDate, Date expirationDate, String roomNumber, String tokenToOpen) {
        return permissionDao.save(new Permission(id, startDate, expirationDate, roomNumber, tokenToOpen));
    }

    public Client addClient(long id){
        return clientDao.save(new Client(id));
    }

    public Client addPermissionToUser(Client client,Permission permission){
        client.getUserPermissionsList().add(permission);
        return clientDao.save(client);
    }

    public Permission getPerm(long id) {
        return permissionDao.findOne(id);
    }

    public Client getLock(long id) {
        return clientDao.findOne(id);
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public PermissionDao getPermissionDao() {
        return permissionDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public ClientService() {
    }


}
