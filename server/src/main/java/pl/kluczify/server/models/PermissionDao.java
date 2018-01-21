package pl.kluczify.server.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PermissionDao extends CrudRepository<Permission, Long> {
    Permission findById(Long id);
}