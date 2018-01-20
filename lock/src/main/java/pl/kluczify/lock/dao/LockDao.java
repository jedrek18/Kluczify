package pl.kluczify.lock.dao;

import org.springframework.data.repository.CrudRepository;
import pl.kluczify.lock.models.Lock;

import javax.transaction.Transactional;

@Transactional
public interface LockDao extends CrudRepository<Lock, Long> {

}