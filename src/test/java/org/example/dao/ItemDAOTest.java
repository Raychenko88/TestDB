package org.example.dao;

import org.example.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    @Test
    void create() {
        Item item = new Item("lalalalala", "newBoroda", 23, 32);
        ItemDAO.create(item);
        assertNotNull(item);

    }

    @Test
    void update() {
        Item item = new Item("newcode", "newchto to", 223, 332);
        item.setId(2);
        ItemDAO.update(item);
        assertEquals(item.getItemCode(), "newcode");

    }

    @Test
    void findById() {
        ItemDAO.findById(2);
        assertEquals("newcode", "newcode");
    }

    @Test
    void findByItemCode() {
        ItemDAO.findByItemCode("newcode");
        assertEquals("newcode", "newcode");
    }

    @Test
    void findByItemPriceBetween() {
    }

    @Test
    void findAll() {
    }
}