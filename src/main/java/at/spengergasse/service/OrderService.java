package at.spengergasse.service;

import at.spengergasse.domain.CoffeeOrderException;
import at.spengergasse.domain.Order;
import at.spengergasse.repository.OrderRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired                                        // ✅ tells Spring to inject this
    private OrderRepository orderRepository;          // ✅ not static

    // ✅ No logic in constructor — remove it entirely or leave it empty
    public OrderService() {}

    @PostConstruct                                    // ✅ runs AFTER injection is done
    public void init() {
        if (orderRepository.count() == 0)
            fillTestData(30);
    }

    public void add(Order order) {                    // ✅ removed static
        orderRepository.save(order);
    }

    public void fillTestData(int anz) {
        Faker faker = new Faker();
        String[] COFFEES = {"Cappucino", "Latte", "Melange", "Espresso", "Mocha", "Americano"};
        String[] SIZES = {"Small", "Medium", "Grande", "Venti"};

        for (int i = 0; i < anz; i++) {
            Order p = new Order();
            //p.setOrderId((long) (i + 1));
            p.setOrderDate(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            p.setCoffee(COFFEES[faker.number().numberBetween(0, COFFEES.length)]);
            p.setSize(SIZES[faker.number().numberBetween(0, SIZES.length)]);
            p.setPrice(faker.number().randomDouble(2, 3, 7));
            p.setQuantity(faker.number().numberBetween(1, 6));
            p.setSirup(faker.bool().bool());
            orderRepository.save(p);
        }
    }

    public ArrayList<Order> findAll() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

    public void removeAll() {
        orderRepository.deleteAll();
    }

    @Override
    public String toString() {
        return orderRepository.findAll().stream()
                .map(Order::toString)
                .collect(Collectors.joining("\n"));
    }

    public void addWrongOrder() {
        Order ord = new Order(LocalDate.now(), "melange", "Venti", -20.0, 1, true);

        if (ord.getPrice() < 3.0) {
            throw new CoffeeOrderException("Price cannot be less than 3 Euro!");  // ✅
        }
        if (ord.getPrice() > 8.0) {
            throw new CoffeeOrderException("Price cannot be  Euro!");  // ✅
        }
        orderRepository.save(ord);
    }

    public void removeOrder(Long orderId) {           // ✅ removed static
        if (orderId == null) {
            throw new CoffeeOrderException("No Order ID!");
        }
        if (!orderRepository.existsById(orderId)) {
            throw new CoffeeOrderException("Order not found!");
        }
        orderRepository.deleteById(orderId);
    }

    public void oneMore(Long orderId) {               // ✅ removed static
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isEmpty()) {
            throw new CoffeeOrderException("Order not found!");
        }
        o.get().setQuantity(o.get().getQuantity() + 1);
        orderRepository.save(o.get());                // ✅ actually persist the change
    }

    public Long getNextOrderId() {
        return orderRepository.findNextOrderId();
    }

    public void update(Order order) {
        orderRepository.save(order);  // save() does UPDATE when id is not null
    }
}