package pl.kluczify.client.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import pl.kluczify.client.model.Client;
import javax.transaction.Transactional;

@Transactional
public interface ClientDao extends CrudRepository<Client, Long> {
}