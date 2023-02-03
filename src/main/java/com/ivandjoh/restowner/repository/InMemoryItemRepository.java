package com.ivandjoh.restowner.repository;

import com.ivandjoh.restowner.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InMemoryItemRepository extends JpaRepository<Item, Long> { }
