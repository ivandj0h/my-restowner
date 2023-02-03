package com.ivandjoh.restowner.controller;

import com.ivandjoh.restowner.entity.Item;
import com.ivandjoh.restowner.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return itemService.createItem(item);
    }

}
