package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity

public class Order implements  Cloneable{
     @Id
    private Long orderId;
    private LocalDate orderDate;
    private String    coffee;
    private String    size;
    private Double    price;
    private Integer   quantity;
    private Boolean   sirup;

     private static final AtomicLong sequence = new AtomicLong(1000);

    public Order(Long orderId, LocalDate orderDate, String coffee, String size, Double price, Integer quantity, Boolean sirup){
        setOrderId(orderId);
        setOrderDate(orderDate);
        setCoffee(coffee);
        setSize(size);
        setPrice(price);
        setQuantity(quantity);
        setSirup (sirup);
    }

    public Order(LocalDate orderDate, String coffee, String size, Double price, Integer quantity, Boolean sirup){
        setOrderDate(orderDate);
        setCoffee(coffee);
        setSize(size);
        setPrice(price);
        setQuantity(quantity);
        setSirup (sirup);
    }

    public Order(){
    }

    public void setOrderId(){
         orderId = sequence.getAndIncrement();
    }

    private static final String[] sizes = { "Klein", "Mittel", "Grande", "Venti" };

    public void setPrice(Double price) {
        if (price < 3)
            throw new CoffeeOrderException("Der min. Preis ist 3.0 Eur");
        if (price > 8)
            throw new CoffeeOrderException("Der min. Preis ist 8.0 Eur");

        this.price = price;
    }

    public void setSize(String size) {
        if (Arrays.asList(sizes).contains(size) == false)
            throw new CoffeeOrderException("Falsche groesse, es muss groesse: " + Arrays.toString(sizes) + " haben");
        this.size = size;
    }

    @Override
    public Order clone(){
       return new Order(orderId, orderDate, coffee, size, price, quantity, sirup);
    }
}
