package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
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

     @NotNull(message = "Order Date is required!")
     @PastOrPresent(message = "The order date cannot be in the Future!")
    private LocalDate orderDate = LocalDate.now();

    @NotBlank(message = "The coffee is required!")
    private String    coffee = "Cappucino";

    @NotBlank(message = "The Size is required!")
    @Pattern(regexp = "Small|Medium|Grande|Venti", message = "Size needs to be Small|Medium|Grande|Venti")
    private String    size = "Medium";

    @NotNull(message = "The Price is required!")
    @DecimalMin(value = "3", message = "The minimum Price is 3 Euros!")
    @DecimalMax(value = "8", message = "The maxmimum Price is 8 Euros!")
    private Double    price = 4.0;

    @NotNull(message = "Quantity is required!")
    @Min(value = 1, message = "The minimum quantity is one!")
    @Max(value = 8, message = "The maxmimum quantity is 8!")
    private Integer   quantity = 1;

    @NotNull(message = "Syrup needs yes or no!")
    private Boolean   sirup = false;

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

    @Override
    public Order clone(){
       return new Order(orderId, orderDate, coffee, size, price, quantity, sirup);
    }
}
