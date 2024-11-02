package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Item;
import org.metsetmerveilles.domain.service.IItemService;
import org.metsetmerveilles.presentation.dto.ItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final IItemService itemService;

    public ItemController(IItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public List<ItemDto> list() {
        return itemService.getAllItems().stream()
                .map(item -> new ItemDto(item.id()))
                .toList();
    }



    @PostMapping
    public ItemDto create(@RequestBody ItemDto itemDto) {
        Item createdItem = itemService.createItem(itemDto.id());

        return new ItemDto(createdItem.id());
    }


    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);

        return new ItemDto(item.id());
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }
}
