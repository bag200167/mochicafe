package at.spengergasse.service;

import at.spengergasse.domain.Order;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private ArrayList<Order> order;

    public OrderService(){
        order = new ArrayList<>(1000);
        fillTestData(500);
    }

    public void fillTestData(int anz) {
        Order p;
        Faker faker;
        String[] COFFEES = {"Cappucino", "Latte", "Melange", "Espresso", "Mocha", "Americano"};
        String[] SIZES = {"Klein", "Mittel", "Grande", "Venti"};

        faker = new Faker();
        order.clear();

        for (int i = 0; i < anz; i++) {
            p = new Order();
            p.setOrderId((long) (i + 1));

            p.setOrderDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            p.setCoffee(COFFEES[faker.number().numberBetween(0, COFFEES.length)]);
            p.setSize(SIZES[faker.number().numberBetween(0, SIZES.length)]);
            p.setPrice(faker.number().randomDouble(2, 3, 8));
            p.setQuantity(faker.number().numberBetween(1, 6));
            p.setSirup(faker.bool().bool());

            order.add(p);
        }
    }

    public ArrayList<Order> findAll (){
        ArrayList<Order> copy = new ArrayList<Order>(order);
        return copy;
    }

    @Override
    public String toString() {
    return order.stream()
            .map(order -> order.toString())
            .collect(Collectors.joining("\n"));
    }
}
