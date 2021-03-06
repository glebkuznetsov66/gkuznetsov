package ru.job4j.tracker;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for created Tracker.
 * @author gkuznetsov.
 * @since 19.09.17.
 */

public class Tracker {
    /**
     * Items of application.
     */
     private ItemStorage items = new ItemStorage();
    /**
     * This method add new element in array Items.
     * @param item - element.
     * @return Item.
     */
    public Item add(Item item) {
        this.items.add(item);
        return item;
    }

    /**
     * This method update selected element.
     * @param item - element.
     */
    public void update(Item item) {
        this.items.update(item);
    }

    /**
     * This element delete selected element.
     * @param item - element.
     */
    public void delete(Item item) {
       this.items.delete(item);
    }

    /**
     * This method find all elements.
     * @return Item[].
     */
    public List<Item> findAll() {
        return this.items.findAll();
    }

    /**
     * This method find element by name.
     * @param key - name of element.
     * @return Item[].
     */
    public List<Item> findByName(String key) {
        return this.items.findAll().stream().filter(i -> i.getName().equals(key)).collect(Collectors.toList());
    }

    /**
     * This method find element by Id.
     * @param id - element id.
     * @return Item.
     */
    public Item findById(String id) {
        return this.items.findAll().stream().filter(i -> id.equals(i.getId())).findFirst().get();
    }

    /**
     * Delete all items from table.
     */
    public void clearTable() {
        this.items.clearTable();
        this.items.closeConnection();
    }


}
