/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.core.service.EntityService;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.dto.ItemUploadDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.items.repository.ItemRepository;
import com.github.ickee953.micros.core.common.Result;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.github.ickee953.micros.core.common.Status.CREATED;
import static com.github.ickee953.micros.core.common.Status.REPLACED;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService implements EntityService<Item, ItemUploadDto> {

    private final ItemRepository itemRepository;

    private final CategoryService categoryService;

    private final ItemPictureService itemPictureService;

    @Override
    public Collection<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item create(ItemUploadDto item, List<MultipartFile> files) {

        Collection<Category> categories = categoryService.getForObject(item);

        Item created = itemRepository.save(
                new Item()
                        .setTitle(item.getTitle())
                        .setDescription(item.getDescription())
                        .setCategory( categories )
        );

        if(files == null || files.isEmpty()) return created;

        try {
            byte[] picture = files.get(0).getBytes();
            itemPictureService.send(created.getId().toString(), picture);
        } catch (IOException e) {
            log.error(String.format("Failed to get file from request: %s", e.getLocalizedMessage()));
        }

        return created;
    }

    @Override
    public Optional<Item> get(UUID id) {
        return itemRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Result<Item> replace(UUID id, ItemUploadDto newItem) {

        Collection<Category> categories = categoryService.getForObject(newItem);

        return itemRepository.findById(id)
                .map( item -> {
                    item.setTitle(newItem.getTitle());
                    item.setDescription(newItem.getDescription());
                    item.setCategory(categories);

                    return new Result<>(REPLACED, itemRepository.save(item));
                })
                .orElseGet(()-> new Result<>(
                        CREATED,
                        itemRepository.save(
                            new Item()
                                .setTitle(newItem.getTitle())
                                .setDescription(newItem.getDescription())
                                .setCategory(categories)
                        )
                ));
    }
}
