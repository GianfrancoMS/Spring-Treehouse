package com.gianfranco.todotoday.dao;

import com.gianfranco.todotoday.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends CrudRepository<Task, Long> {
    @Query("select t from Task t where t.user.id=:#{principal.id}")
    List<Task> findAll();
}
