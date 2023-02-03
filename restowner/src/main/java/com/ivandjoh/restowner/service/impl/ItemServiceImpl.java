package com.ivandjoh.restowner.service.impl;

import com.ivandjoh.restowner.entity.Item;
import com.ivandjoh.restowner.repository.InMemoryItemRepository;
import com.ivandjoh.restowner.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private InMemoryItemRepository repository;

    public ItemServiceImpl(InMemoryItemRepository inMemoryItemRepository) {
        this.repository = inMemoryItemRepository;
        this.repository.saveAll(defaultItems());
    }

    private static List<Item> defaultItems() {
        return List.of(
                new Item(1L, "Pizza", 100L, "Pizza with cheese", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"),
                new Item(2L, "Burger", 200L, "Burger with cheese", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"),
                new Item(3L, "Fries", 300L, "Fries with cheese", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
        );
    }

    public List<Item> findAll() {
        List<Item> listItem = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(listItem::add);
        return listItem;
    }

    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    public ResponseEntity<Item> createItem(Item item) {

        Item newItem = new Item(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        Item savedItem = repository.save(newItem);
        return ResponseEntity.ok(savedItem);
    }

    public ResponseEntity<Item> updateItem(Long id, Item item) {
        return repository.findById(id)
                .map(record -> {
                    record.setName(item.getName());
                    record.setPrice(item.getPrice());
                    record.setDescription(item.getDescription());
                    record.setImage(item.getImage());
                    Item updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Item>> getItems() {
        List<Item> items = repository.findAll();
        return ResponseEntity.ok(findAll());
    }

    @Override
    public ResponseEntity<Item> getItemById(Long id) {
        Optional<Item> item = repository.findById(id);
        return item
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }


}
