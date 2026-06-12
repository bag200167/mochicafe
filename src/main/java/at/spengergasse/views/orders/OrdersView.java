package at.spengergasse.views.orders;

import at.spengergasse.domain.CoffeeOrderException;
import at.spengergasse.domain.Order;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.LocalDate;

@PageTitle("Orders")
@Route("orders")
@Menu(order = 1, icon = LineAwesomeIconUrl.COFFEE_SOLID)
public class OrdersView extends VerticalLayout {

    private final Button buttonRemoveAllOrders = new Button("Remove All Orders");
    private final Button buttonAdd10Orders = new Button("Add 10 Orders");
    private final Button buttonAddWrong = new Button("Add WRONG order");


    private final Grid<Order> grid = new Grid<>(Order.class, false);
    private final OrderService coffeeOrderService;

    public OrdersView(@Autowired OrderService coffeeOrderService) {
        this.coffeeOrderService = coffeeOrderService;
        setSpacing(true);
        
        buttonAddWrong.addClickListener(b -> addWrongOrder());
        buttonRemoveAllOrders.addClickListener(b -> removeAllOrders());
        buttonAdd10Orders.addClickListener(b -> add10Orders());
        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAllOrders,buttonAdd10Orders,buttonAddWrong);
        buttons.setSpacing(true);

        grid.addColumn(o -> o.getOrderId())
                .setHeader("Order ID")
                .setSortable(true);
        grid.addColumn(o -> o.getOrderDate())
                .setHeader("Order Date")
                .setSortable(true);
        grid.addColumn(o -> o.getCoffee())
                .setHeader("Coffee")
                .setSortable(true);
        Image p = new Image("icons/coffee.png", "Coffee");
        p.setWidth("64px");
        grid.addColumn(o -> o.getSize())
                .setHeader(new HorizontalLayout(p, new Span("Size")))
                .setSortable(true);
        grid.addColumn(o -> o.getQuantity())
                .setHeader("Quantity")
                .setSortable(true);

        grid.addComponentColumn(o -> {
                    Checkbox cb = new Checkbox(o.getSirup());
                    cb.setReadOnly(true);
                    return cb;
                })
                .setHeader("Syrup")
                .setSortable(true)
                .setComparator(o -> o.getSirup());

        grid.addComponentColumn(o -> {
                    Button remove = new Button("Delete");
                    remove.addClickListener(e -> removeOrder(o.getOrderId()));
                    return remove;
                })
                .setHeader("Task")
                .setSortable(false);

        grid.addComponentColumn(o -> new Button("One more", e -> oneMore(o.getOrderId())))
                .setHeader("Task")
                .setSortable(false);

        setSizeFull();
        grid.setSizeFull();
        add(buttons);
        add(grid);
        reload();
    }

    private void oneMore(Long orderId) {
        try {
            OrderService.oneMore(orderId);
            reload();
        }
        catch (CoffeeOrderException e) {
            Notification.show(e.getMessage());
        }
    }

    private void removeOrder(Long orderId) {
        try{
            OrderService.removeOrder(orderId);
            reload();
        }catch (CoffeeOrderException e) {
            Notification.show(e.getMessage());
        }

    }

    private void addWrongOrder() {
        try{
            coffeeOrderService.addWrongOrder();
            reload();
        } catch (CoffeeOrderException e){
            Notification.show(e.getMessage());
        }
    }

    private void removeAllOrders() {
        try{
        coffeeOrderService.removeAll();
        buttonRemoveAllOrders.setEnabled(true);
        reload();
        } catch (CoffeeOrderException e){
            Notification.show(e.getMessage());
        }
    }

    private void add10Orders() {
        try{
        coffeeOrderService.fillTestData(10);
        buttonRemoveAllOrders.setEnabled(true);
        reload();
        } catch (CoffeeOrderException e){
            Notification.show(e.getMessage());
        }
    }

    private void reload() {
        grid.setItems(coffeeOrderService.findAll());
    }

}
