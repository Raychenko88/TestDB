package org.example.service;

import org.example.dao.ItemDAO;
import org.example.model.Item;

import java.util.List;

public class ItemService {


    public  static Item create(Item item){
        for (Item a: ItemService.findByItemCode(item.getItemCode())) {
            if (a.getName().equals(item.getName())){
                return null;
            }else {
                ItemDAO.create(item);
            }
        }
        return item;
    }
    public static Item update(Item item){
        if (ItemService.create(item) == null){
            ItemDAO.update(item);
            return item;
        }
        return null;
    }

    public static void delete(Item item){
    ItemDAO.delete(item);
    }

    public static Item findById(Item item){
       return ItemDAO.findById(item.getId());
    }

    public static List<Item> findByItemCode(String code){
        return ItemDAO.findByItemCode(code);
    }

    public static List<Item> findByItemPriceBetween(Integer min, Integer max){
        return ItemDAO.findByItemPriceBetween(min,max);
    }

    private static List<Item> findAll(){
        return ItemDAO.findAll();
    }
}
