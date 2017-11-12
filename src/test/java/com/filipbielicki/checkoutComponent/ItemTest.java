package com.filipbielicki.checkoutComponent;

import com.filipbielicki.checkoutComponent.model.Item;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    private static final Long ID = 1L;
    private static final double PRICE = 30;
    private static final int MIN_QTY_FOR_SPEC_PRICE = 3;
    private static final double SPEC_PRICE = 70;
    private Item testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new Item();
        testedObject.setId(ID);
        testedObject.setPrice(PRICE);
        testedObject.setQuantityForSpecialPrice(MIN_QTY_FOR_SPEC_PRICE);
        testedObject.setSpecialPrice(SPEC_PRICE);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(1);
        assertThat(testedObject.getPrice()).isEqualTo(30);
        assertThat(testedObject.getQuantityForSpecialPrice()).isEqualTo(3);
        assertThat(testedObject.getSpecialPrice()).isEqualTo(70);
    }
}
