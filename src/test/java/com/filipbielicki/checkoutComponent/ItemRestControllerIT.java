package com.filipbielicki.checkoutComponent;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemRestControllerIT {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnCorrectHttpResponseAndContent() throws Exception {
        String getOneItem = "/allrecords/items/ids/{itemid}";

        Long itemId = 4L;

        mockMvc.perform(get(getOneItem, itemId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"id\":4,\"price\":25.0,\"quantityForSpecialPrice\":2,\"specialPrice\":40.0}", true))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void shouldReturnCorrectHttpResponseAndCalculatedPrice() throws Exception {

        String getTotalPrice = "/allrecords/items/ids/{id}/{qty}";

        Long itemId = 1L;
        int quantity = 8;

        mockMvc.perform(get(getTotalPrice, itemId, quantity))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("220.0", true))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void shouldReturn404HttpResponseWhenItemNotFound() throws Exception {

        String getTotalPrice = "/allrecords/items/ids/{id}/{qty}";

        Long itemId = 1000L;
        int quantity = 8;

        mockMvc.perform(get(getTotalPrice, itemId, quantity))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnCorrectHttpResponseAndInsertNewItem() throws Exception {

        String insertNewItem = "/allrecords/insert/{price}/{qty}/{specprice}";

        double price = 35;
        int quantity = 5;
        double specialPrice = 135;

        mockMvc.perform(post(insertNewItem, price, quantity, specialPrice))
                .andExpect(status().isOk())
                .andDo(print());
    }
}