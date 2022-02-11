package com.linda.todo.repository;

import com.linda.todo.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByDescContaining(String desc);

    @Transactional
    @Query(value = "SELECT * FROM EVENT WHERE description=:desc", nativeQuery = true)
    EventEntity selectByDesc(@Param(value = "desc")String desc);
}