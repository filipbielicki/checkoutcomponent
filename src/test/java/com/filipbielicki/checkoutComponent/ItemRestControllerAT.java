package com.filipbielicki.checkoutComponent;

import com.filipbielicki.checkoutComponent.controller.ItemRestController;
import com.filipbielicki.checkoutComponent.model.Item;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemRestControllerAT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    ItemRestController itemRestController;

    @Test//assumed that DB contains item with id=1 and its price
    public void shouldReturnSpecificItemWhenIdPassed() throws Exception {

        assertThatCode(() -> {
            itemRestController.getItemById(1L);
        }).doesNotThrowAnyException();

        Item searchedItem = itemRestController.getItemById(1L);

        assertThat(searchedItem).isNotNull();
        assertThat(searchedItem.getId()).isEqualTo(1L);
        assertThat(searchedItem.getPrice()).isEqualTo(40);
    }

    @Test//assumed that DB contains item with id=3 with price, qty_for_spec_price, spec_price columns filled
         //as follows: 30, 4, 60 | should return 0 when 0 passed as quantity
    public void shouldReturnCorrectlyCalculatedPriceWhenItemIdAndQuantityPassed() throws Exception {

        assertThatCode(() -> {
          itemRestController.getTotalPriceByItemIdAndQuantity(3L,7);
          itemRestController.getTotalPriceByItemIdAndQuantity(3L,0);
        }).doesNotThrowAnyException();

        double calculatedItemPrice = itemRestController.getTotalPriceByItemIdAndQuantity(3L,7);
        double calculatedItemPrice0 = itemRestController.getTotalPriceByItemIdAndQuantity(3L,0);

        assertThat(calculatedItemPrice).isGreaterThan(0);
        assertThat(calculatedItemPrice).isEqualTo(150);
        assertThat(calculatedItemPrice0).isEqualTo(0);
    }

    @Test//assumed that DB does not contain item with id=100
    public void shouldThrowNotFoundExceptionWhenItemNotFound() throws NotFoundException {

        thrown.expect(NotFoundException.class);

        Item searchedItem = itemRestController.getItemById(100L);
        double searchedItem2 = itemRestController.getTotalPriceByItemIdAndQuantity(100L,7);

        assertThat(searchedItem).isNull();
        assertThat(searchedItem2).isEqualTo(0);
    }
}

