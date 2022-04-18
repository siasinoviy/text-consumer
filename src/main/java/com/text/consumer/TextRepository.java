package com.text.consumer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Sergii
 */
public interface TextRepository extends CrudRepository<TextEntity, Long> {
    List<TextEntity> findTop10ByOrderByIdDesc();
}
