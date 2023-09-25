import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

package ad221.milstein;

public class Main {

    public static void main(String[] args) {
        // Створюємо об'єкт класу "Кошик" з максимальною кількістю елементів у стеку
        Cart cartUsingStack = new Cart(10);

        // Заповнюємо кошик об'єктами класу Item
        cartUsingStack.addItem(new Item("Item1", 10.0f));
        cartUsingStack.addItem(new Item("Item2", 15.0f));
        cartUsingStack.addItem(new Item("Item3", 20.0f));
        cartUsingStack.addItem(new Item("Item4", 25.0f));
        cartUsingStack.addItem(new Item("Item5", 30.0f));

        // Виводимо суму цін товарів усередині кошика
        float totalPriceUsingStack = cartUsingStack.calculateTotalPrice();
        System.out.println("Сума цін товарів у кошику: " + totalPriceUsingStack);

        // Піднімаємо ціни в кошику на 15%
        cartUsingStack.increasePrices(15);

        // Виводимо змінену суму цін
        float newTotalPriceUsingStack = cartUsingStack.calculateTotalPrice();
        System.out.println("Змінена сума цін після підняття цін на 15%: " + newTotalPriceUsingStack);

        // Знижуємо ціни в кошику на 30%
        cartUsingStack.decreasePrices(30);

        // Виводимо змінену суму цін
        float finalTotalPriceUsingStack = cartUsingStack.calculateTotalPrice();
        System.out.println("Змінена сума цін після зниження цін на 30%: " + finalTotalPriceUsingStack);

        // Переписаний клас Cart, використовуючи чергу замість стеку
        Cart cartUsingQueue = new Cart(10);

        cartUsingQueue.addItem(new Item("Item6", 35.0f));
        cartUsingQueue.addItem(new Item("Item7", 40.0f));

        float totalPriceUsingQueue = cartUsingQueue.calculateTotalPrice();
        System.out.println("Сума цін товарів у кошику (черга): " + totalPriceUsingQueue);
    }
}

class Item {
    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public void increasePrice(float percent) {
        if (percent > 0) {
            price += price * (percent / 100);
        }
    }

    public void decreasePrice(float percent) {
        if (percent > 0) {
            price -= price * (percent / 100);
            if (price < 0) {
                price = 0;
            }
        }
    }

    public float getPrice() {
        return price;
    }
}

class Cart {
    private Queue<Item> items;
    private int maxSize;

    public Cart(int maxSize) {
        this.maxSize = maxSize;
        items = new ArrayDeque<>();
    }

    public void addItem(Item item) {
        if (items.size() < maxSize) {
            items.offer(item);
        } else {
            System.out.println("Кошик заповнений. Неможливо додати більше товарів.");
        }
    }

    public void removeItem() {
        if (!items.isEmpty()) {
            items.poll();
        } else {
            System.out.println("Кошик порожній. Неможливо видалити товар.");
        }
    }

    public float calculateTotalPrice() {
        float total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void increasePrices(float percent) {
        for (Item item : items) {
            item.increasePrice(percent);
        }
    }

    public void decreasePrices(float percent) {
        for (Item item : items) {
            item.decreasePrice(percent);
        }
    }
}
