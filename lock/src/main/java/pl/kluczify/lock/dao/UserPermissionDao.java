package pl.kluczify.lock.dao;

import org.springframework.data.repository.CrudRepository;
import pl.kluczify.lock.models.UserPermission;

import javax.transaction.Transactional;

@Transactional
public interface UserPermissionDao extends CrudRepository<UserPermission, Long> {

}
