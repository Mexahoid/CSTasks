package vsu.kurs3.att1.task3.hibNormal.repositories;

import org.springframework.data.repository.CrudRepository;
import vsu.kurs3.att1.task3.hibNormal.models.Group;

public interface GroupRepository  extends CrudRepository<Group, Long> {

    Iterable<Group> findAllByOrderByNumberAsc();
}