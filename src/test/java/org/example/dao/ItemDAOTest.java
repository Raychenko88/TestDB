package org.example.dao;

import org.example.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    @Test
    void create() {
        Item item = new Item("asd", "chto to", "skolyko to");
        ItemDAO.create(item);
        assertNotNull(item);
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByItemCode() {
    }

    @Test
    void findByItemPriceBetween() {
    }

    @Test
    void findAll() {
    }
}