package com.filipbielicki.checkoutComponent;

import com.filipbielicki.checkoutComponent.controller.ItemRestController;
import com.filipbielicki.checkoutComponent.model.Item;
import com.filipbielicki.checkoutComponent.service.item.ItemService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class ItemRestControllerTest {

    private ItemRestController itemRestController;

    @Mock
    private ItemService mockedItemService;

    @Before
    public void setUp(){
        itemRestController = new ItemRestController(mockedItemService);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long ID_3 = 3L;
    private static final Long ID_4 = 4L;

    private static final int QTY_6 = 6;
    private static final int QTY_5 = 5;
    private static final int QTY_10 = 10;
    private static final int QTY_7 = 7;

    private static final double HUNDRED_FORTY = 140;
    private static final double FORTY = 40;
    private static final double HUNDRED_EIGHTY = 180;
    private static final double HUNDRED_FORTY_FIVE = 145;

    private Object[][] parametersForSpecificSearchedItemsToGetPrice() {
        return new Object[][]{
                {ID_1, QTY_6, HUNDRED_FORTY},
                {ID_2, QTY_5, FORTY},
                {ID_3, QTY_10, HUNDRED_EIGHTY},
                {ID_4, QTY_7, HUNDRED_FORTY_FIVE}
        };
    }

    @Test
    @Parameters(method = "parametersForSpecificSearchedItemsToGetPrice")
    public void shouldReturnCorrectPriceWhenParametersPassed(
            Long passedId, int quantity, double expectedTotalPrice) throws Exception {
        when(itemRestController.getTotalPriceByItemIdAndQuantity(passedId, quantity)).thenReturn(expectedTotalPrice);
        double result = itemRestController.getTotalPriceByItemIdAndQuantity(passedId, quantity);

        assertThat(result).isEqualTo(expectedTotalPrice);
    }

    @Test
    public void shouldReturnItemSearchedById() throws Exception {
        Item itemSearched = new Item(40,3,70);
        itemSearched.setId(1L);
        when(itemRestController.getItemById(1L)).thenReturn(itemSearched);
        Item resultItem = itemRestController.getItemById(1L);

        assertThat(resultItem.getId()).isEqualTo(1L);
        assertThat(resultItem.getPrice()).isEqualTo(40);
        assertThat(resultItem.getQuantityForSpecialPrice()).isEqualTo(3);
        assertThat(resultItem.getSpecialPrice()).isEqualTo(70);
    }


    @Test
    public void shouldThrowNotFoundExceptionWhenItemNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        when(itemRestController.getItemById(10L)).thenThrow(NotFoundException.class);
        Item searchedItem = itemRestController.getItemById(10L);

        assertThat(searchedItem).isNull();
    }

    @Test
    public void shouldReturnAllRecords() throws Exception {
        List<Item> itemList = getItemsList();
        when(itemRestController.getAllItems()).thenReturn(itemList);
        List<Item> allItemsList = itemRestController.getAllItems();

        assertThat(allItemsList.get(0).getId()).isEqualTo(1L);
        assertThat(allItemsList.get(1).getId()).isEqualTo(2L);
        assertThat(allItemsList.get(2).getId()).isEqualTo(3L);
        assertThat(allItemsList.get(3).getId()).isEqualTo(4L);
    }

    @Test
    public void shouldAddNewRecordToDB() throws Exception {
        itemRestController.insertNewItem(20,3,50);
        verify(mockedItemService, times(1)).insertNewItemIntoDB(Mockito.any(Item.class));
    }

    private List<Item> getItemsList(){
        Item item1 = new Item(40,3,70);
        Item item2 = new Item(10,2,15);
        Item item3 = new Item(30,4,60);
        Item item4 = new Item(25,2,40);
        item1.setId(1L);
        item2.setId(2L);
        item3.setId(3L);
        item4.setId(4L);

        List<Item> listToSave = new ArrayList<>();

        listToSave.add(item1);
        listToSave.add(item2);
        listToSave.add(item3);
        listToSave.add(item4);

        return listToSave;
    }
}
