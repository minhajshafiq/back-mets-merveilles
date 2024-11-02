package org.metsetmerveilles.domain.service;

import org.metsetmerveilles.data_access.entity.ItemEntity;
import org.metsetmerveilles.data_access.repository.ItemRepository;
import org.metsetmerveilles.domain.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll().stream()
                .map(itemEntity -> new Item(itemEntity.getId()))
                .toList();
    }

    @Override
    public Item createItem(Long item) {
        ItemEntity itemEntity = itemRepository.save(new ItemEntity());
        return new Item(itemEntity.getId());
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .map(itemEntity -> new Item(itemEntity.getId()))
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

}
