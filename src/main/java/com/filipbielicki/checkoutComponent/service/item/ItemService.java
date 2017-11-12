package com.filipbielicki.checkoutComponent.service.item;

import com.filipbielicki.checkoutComponent.NotFoundException;
import com.filipbielicki.checkoutComponent.model.Item;
import com.filipbielicki.checkoutComponent.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository = null;

    public ItemService(ItemRepository repository) {
        this.itemRepository = repository;
    }

    @Autowired
    private DiscountCalculator discountCalculator;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) throws NotFoundException {
        return getExisitngItem(id);
    }

    public void insertNewItemIntoDB(Item item) {
        itemRepository.save(item);
    }

    public double getPriceBySelectedItemIdAndQuantity(Long id, int quantity) throws NotFoundException {
        return calculatePriceWhenItemandQuantitySelected(id, quantity);
    }

    private Item getExisitngItem(Long id) throws NotFoundException {
        Item itemToFind = getFoundItem(id);

        if (itemToFind != null) {
            return itemRepository.findItemById(id);
        } else {
            throw new NotFoundException();
        }
    }


    private Item getFoundItem(Long id) {
        Item foundItem = new Item();
        List<Item> items = itemRepository.findAll();

        for (Item item : items) {
            if (item.getId().equals(id)) {
                foundItem = item;
                return foundItem;
            }
            foundItem = null;
        }
        return foundItem;
    }

    private double calculatePriceWhenItemandQuantitySelected(Long id, int quantity) throws NotFoundException {
        double totalPrice = 0;
        Item itemSearched = getFoundItem(id);

        if(itemSearched == null){
            throw new NotFoundException();
        }
        if (itemSearched.getId().equals(id)) {
            if (quantity % itemSearched.getQuantityForSpecialPrice() == 0) {
                totalPrice = discountCalculator.calculateDiscountedPriceWhenQuantityEven(
                        itemSearched.getSpecialPrice(), quantity, itemSearched.getQuantityForSpecialPrice());
            } else {
                totalPrice = discountCalculator.calculateDiscountedPriceWhenQuantityIsUneven(
                        quantity, itemSearched.getQuantityForSpecialPrice(), itemSearched.getPrice(),
                        itemSearched.getSpecialPrice());
            }
        }
        return totalPrice;
    }
}
