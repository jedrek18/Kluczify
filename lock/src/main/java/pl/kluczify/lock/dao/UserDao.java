package pl.kluczify.lock.dao;

import org.springframework.data.repository.CrudRepository;
import pl.kluczify.lock.models.User;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

}
