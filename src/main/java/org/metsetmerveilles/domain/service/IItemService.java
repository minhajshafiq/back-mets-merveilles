package org.metsetmerveilles.domain.service;

import org.metsetmerveilles.domain.model.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAllItems();

    Item createItem(Long item);

    Item getItemById(Long id);

    void deleteItemById(Long id);
}
