package at.spengergasse.service;

import at.spengergasse.domain.CoffeeOrderException;
import at.spengergasse.domain.Order;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static ArrayList<Order> orders;

    public OrderService(){
        orders = new ArrayList<>(1000);
        fillTestData(500);
    }

    public static void add(Order order) {
        orders.add(order);
    }

    public void fillTestData(int anz) {
        Order p;
        Faker faker;
        String[] COFFEES = {"Cappucino", "Latte", "Melange", "Espresso", "Mocha", "Americano"};
        String[] SIZES = {"Small", "Medium", "Grande", "Venti"};

        faker = new Faker();

        for (int i = 0; i < anz; i++) {
            p = new Order();
            p.setOrderId((long) (i + 1));

            p.setOrderDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            p.setCoffee(COFFEES[faker.number().numberBetween(0, COFFEES.length)]);
            p.setSize(SIZES[faker.number().numberBetween(0, SIZES.length)]);
            p.setPrice(faker.number().randomDouble(2, 3, 7));
            p.setQuantity(faker.number().numberBetween(1, 6));
            p.setSirup(faker.bool().bool());

            orders.add(p);
        }
    }

    public ArrayList<Order> findAll (){
        ArrayList<Order> copy = new ArrayList<Order>(orders);
        return copy;
    }

    public void removeAll() {
        orders.clear();
    }

    @Override
    public String toString() {
    return orders.stream()
            .map(order -> order.toString())
            .collect(Collectors.joining("\n"));
    }

    public void addWrongOrder() {
        Order ord;

        // -20 Euro!!!
        ord = new Order(LocalDate.now(), "melange", "Venti", -20.0, 1, true);
        orders.add(ord);
    }

    public void removeOrder_old(Long orderId) {
            Order o;
            Iterator<Order> it;
            int anz;

            anz = 0;
            if (orderId == null)
                throw new CoffeeOrderException("No Order ID!");
            it = orders.iterator();
            while (it.hasNext()) {
                o = it.next();
                if (o.getOrderId().equals(orderId)) {
                    it.remove();
                    anz++;
                }
            }
            if (anz == 0)
                throw new CoffeeOrderException("Order with the ID " + orderId + " not found!");
        }

    public static void removeOrder(Long orderId) {
        if(orderId == null){
            throw new CoffeeOrderException("No Order ID!");
        }
        boolean found = orders.removeIf(o -> o.getOrderId().equals(orderId));
        if(!found){
            throw new CoffeeOrderException("Order not found!");
        }
    }

    public static void oneMore(Long orderId) {
        if(orderId == null){
            throw new CoffeeOrderException("No Order ID!");
        }
        orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .forEach(order -> order.setQuantity(order.getQuantity()+1));
    }

    public void oneMore_old(Long orderId) {
        for (Order o : orders) {
            if (o.getOrderId().equals(orderId)) {
                o.setQuantity(o.getQuantity() + 1);
                return;
            }
        }
        throw new CoffeeOrderException("Order not found!");
    }
}
