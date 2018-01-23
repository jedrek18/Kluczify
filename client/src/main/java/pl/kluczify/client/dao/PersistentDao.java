package pl.kluczify.client.dao;

import org.springframework.stereotype.Repository;
import pl.kluczify.client.model.Client;
import pl.kluczify.client.model.Permission;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersistentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Client clientSave(Client client){
        if(client.getId() == null){
            entityManager.persist(client);
            return client;
        }
        else {
            return entityManager.merge(client);
        }
    }

    public Permission clientSave(Permission perm){
        if(perm.getId() == null){
            entityManager.persist(perm);
            return perm;
        }
        else {
            return entityManager.merge(perm);
        }
    }




}
