package com.alex;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;
    private final Map<StockItem, Integer> listOfReserved;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
        this.listOfReserved = new TreeMap<>();

    }

    public int addToBasket(StockItem item, int quantity, int reservedQuantity) {
        int inBasket = list.getOrDefault(item, 0);
        int inBasketReserved = listOfReserved.getOrDefault(item, 0);
        if ((item != null) && (inBasket + quantity >= 0) && (inBasketReserved + reservedQuantity >= 0)) {
            list.put(item, inBasket + quantity);
            listOfReserved.put(item, inBasketReserved + reservedQuantity);
            return inBasket;
        }
        return 0;
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    public Map<StockItem, Integer> ReservedItems() {
        return Collections.unmodifiableMap(listOfReserved);


    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased and " + listOfReserved.get(item.getKey()) + " reserved\n";

            totalCost += item.getKey().getPrice() * item.getValue();


        }

        return s + "Total cost " + totalCost;
    }

    public void checkOut() {
        for (Map.Entry<StockItem, Integer> item : listOfReserved.entrySet()) {

            int sellReserve = -item.getValue();


            item.getKey().adjustReservedStock(sellReserve);

            item.getKey().adjustStock(sellReserve);
        }
        list.clear();
        listOfReserved.clear();
    }
}
