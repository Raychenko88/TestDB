package org.example.dao;

import org.example.model.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    @Test
    void create() {
        Item item = new Item("Aa321FqZ", "SomeThing", 500, 25);
        ItemDAO.create(item);
        assertNotNull(item);

    }

    @Test
    void update() {
        Item item = new Item("AS456789RES", "Guitar", 18000, 3);
        item.setId(4);
        ItemDAO.update(item);
        assertEquals("AS456789RES", item.getItemCode());

    }

    @Test
    void findById() {
        assertEquals(4, ItemDAO.findById(4).getId());
    }

    @Test
    void findByItemCode() {
        List<Item> items = ItemDAO.findByItemCode("AS456789RES");
        assertEquals("AS456789RES", items.get(0).getItemCode());
    }

    @Test
    void findByItemPriceBetween() {
        assertEquals(3, ItemDAO.findByItemPriceBetween(23, 18000).size());
    }

    @Test
    void findAll() {
        List <Item> items = ItemDAO.findAll();
        assertEquals(3, items.size());
    }
}
