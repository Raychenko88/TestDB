package org.example.dao;

import org.example.model.Item;
import org.example.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {
    private List<Item> testItems = new ArrayList<>();

    @AfterEach
    public void clearData(){
        testItems.forEach(ItemDAO::delete);
        testItems.clear();
    }

    @Test
    void create() {
        Item item = new Item("Aa321FqZ", "SomeThing", 500, 25);
        ItemDAO.create(item);
        testItems.add(item);
        assertNotNull(item.getId());

    }

    @Test
    void update() {
        Item item = new Item("AE3123Rf", "Guitar", 18000, 3);
        ItemDAO.create(item);
        testItems.add(item);
        item.setName("tname");
        ItemDAO.update(item);
        assertEquals("tname", ItemDAO.findById(item.getId()).getName());

    }

    @Test
    void findById() {
        Item item = new Item("AE3123Rf", "Guitar", 18000, 3);
        ItemDAO.create(item);
        testItems.add(item);
        assertEquals(item.getId(), ItemDAO.findById(item.getId()).getId());
    }

    @Test
    void findByItemCode() {
        Item item = new Item("AE3123Rf", "Guitar", 18000, 3);
        ItemDAO.create(item);
        testItems.add(item);
        List<Item> items = ItemDAO.findByItemCode("AE3123Rf");
        assertEquals("AE3123Rf", items.get(0).getItemCode());
    }

    @Test
    void findByItemPriceBetween() {
        int allItems = ItemDAO.findByItemPriceBetween(18000, 23000).size() + 2;
        Item item1 = new Item("AE3123Rf", "Guitar", 18000, 3);
        Item item2 = new Item("TAE3123Rf", "tname", 23000, 2);
        ItemDAO.create(item1);
        ItemDAO.create(item2);
        testItems.add(item1);
        testItems.add(item2);
        assertEquals(allItems, ItemDAO.findByItemPriceBetween(18000, 23000).size());
    }

    @Test
    void findAll() {
        int allItems = ItemDAO.findAll().size() + 2;
        Item item1 = new Item("AE3123Rf", "Guitar", 18000, 3);
        Item item2 = new Item("TAE3123Rf", "tname", 23000, 2);
        ItemDAO.create(item1);
        ItemDAO.create(item2);
        testItems.add(item1);
        testItems.add(item2);
        List <Item> items = ItemDAO.findAll();
        assertEquals(allItems, ItemDAO.findAll().size());
    }
    @Test
    void delete(){
        Item item = new Item("AE3123Rf", "Guitar", 18000, 3);
        ItemDAO.create(item);
        testItems.add(item);
        assertNotNull(ItemDAO.findById(item.getId()));
        ItemDAO.delete(item);
        assertNull(ItemDAO.findById(item.getId()));
    }
}