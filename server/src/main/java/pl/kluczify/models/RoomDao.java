package pl.kluczify.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoomDao extends CrudRepository<Room, Long> {
}
