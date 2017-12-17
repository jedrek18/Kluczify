package pl.kluczify.server.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoomDao extends CrudRepository<Room, Long> {
}
