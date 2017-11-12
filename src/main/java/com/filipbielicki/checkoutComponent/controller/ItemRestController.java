package com.filipbielicki.checkoutComponent.controller;

import com.filipbielicki.checkoutComponent.NotFoundException;
import com.filipbielicki.checkoutComponent.model.Item;
import com.filipbielicki.checkoutComponent.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allrecords")
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    public ItemRestController(ItemService service) {
        this.itemService = service;
    }

    @RequestMapping(value = "")
    @ResponseBody
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/items/ids/{itemid}")
    @ResponseBody
    public Item getItemById(@PathVariable("itemid") Long itemId) throws NotFoundException {
        return itemService.getItemById(itemId);
    }


    @RequestMapping(value = "/items/ids/{itemid}/{qty}")
    @ResponseBody
    public double getTotalPriceByItemIdAndQuantity(@PathVariable("itemid") Long id,
                                               @PathVariable("qty") int qty) throws NotFoundException {
        return itemService.getPriceBySelectedItemIdAndQuantity(id, qty);
    }

    @RequestMapping(value = "/insert/{price}/{qty}/{specprice}")
    public void insertNewItem(@PathVariable("price") double price,
                              @PathVariable("qty") int qtyForSpecPrice,
                              @PathVariable("specprice") double specPrice) {
        itemService.insertNewItemIntoDB(new Item(price, qtyForSpecPrice, specPrice));
    }
}


