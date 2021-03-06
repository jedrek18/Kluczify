package pl.kluczify.client.dao;


import pl.kluczify.client.model.Permission;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PermissionDao extends CrudRepository<Permission, Long> {
}