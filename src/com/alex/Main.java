package com.alex;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

//        System.out.println(stockList);

//        for (String s : stockList.Items().keySet()) {
//            System.out.println(s);
//        }
        System.out.println(stockList);

        Basket timsBasket = new Basket("Tim");
        reserveItem(timsBasket, "cup", 20);
        reserveItem(timsBasket, "car", 20);
        System.out.println(timsBasket);

        Basket tomsBasket = new Basket("Tom");
        reserveItem(tomsBasket, "cup", 20);
        System.out.println(tomsBasket);

        System.out.println(stockList.get("cup").getReserved());

        unreserveItem(timsBasket, "car", 19);
        System.out.println(timsBasket);
//        System.out.println(stockList.get("cup").getReserved());

        timsBasket.checkOut();
        System.out.println(timsBasket);
        System.out.println(stockList);

//        reserveItem(timsBasket, "car", 1);
//        System.out.println(timsBasket);
//
//        reserveItem(timsBasket, "car", 1);
//        reserveItem(timsBasket, "spanner", 5);
//        System.out.println(timsBasket);
//
//        reserveItem(timsBasket, "juice", 4);
//        reserveItem(timsBasket, "cup", 12);
//        reserveItem(timsBasket, "bread", 1);
//        System.out.println(timsBasket);

//        System.out.println(stockList);

//        stockList.Items().get("car").adjustStock(2000);
//        stockList.get("car").adjustStock(-1000);
//        System.out.println(stockList);
//        for (Map.Entry<String, Double> price : stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }
    }

    public static int reserveItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        int quantityForReserve = quantity;

        if (quantity < 0) {
            int res = basket.ReservedItems().getOrDefault(stockItem, 0);
            int inB = basket.Items().getOrDefault(stockItem, 0);
            quantityForReserve = quantity + (inB - res);
            if ((quantityForReserve > 0)||(-quantity > inB)) {
                quantityForReserve = 0;
            }
        }
        int reservedQuantity = stockList.changeReserveStock(item, quantityForReserve);

        basket.addToBasket(stockItem, quantity, reservedQuantity);
        return quantity;
    }

    public static int unreserveItem(Basket basket, String item, int quantity) {
        return reserveItem(basket, item, -quantity);
    }

}



