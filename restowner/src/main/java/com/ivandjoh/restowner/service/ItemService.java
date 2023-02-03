package com.ivandjoh.restowner.service;

import com.ivandjoh.restowner.entity.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemService {
    ResponseEntity<List<Item>> getItems();

    ResponseEntity<Item> getItemById(Long id);

    ResponseEntity<Item> createItem(Item item);
}
