package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.ItemEntity;
import com.swiftbeard.ecommerce_app.model.Item;

import java.util.List;

public interface ItemService {

    ItemEntity toEntity(Item m);

    List<ItemEntity> toEntityList(List<Item> items);

    Item toModel(ItemEntity e);

    List<Item> toModelList(List<ItemEntity> items);
}
