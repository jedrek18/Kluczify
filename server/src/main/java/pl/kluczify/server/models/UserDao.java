package pl.kluczify.server.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by jedrek on 17.12.17.
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
}
