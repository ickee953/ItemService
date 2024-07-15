/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.controller;

import com.github.ickee953.micros.core.controller.CrudController;
import com.github.ickee953.micros.items.dto.ItemUploadDto;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.items.service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController extends CrudController<Item, ItemUploadDto> {

    public ItemController(ItemService itemService) {
        super(itemService);
    }

}