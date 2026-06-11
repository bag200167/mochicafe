package at.spengergasse.views.orders;

import at.spengergasse.domain.CoffeeOrderException;
import at.spengergasse.domain.Order;
import at.spengergasse.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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


    private final Grid<Order> grid = new Grid<>(Order.class, true);
    private final OrderService coffeeOrderService;

    public OrdersView(@Autowired OrderService coffeeOrderService) {
        this.coffeeOrderService = coffeeOrderService;
        setSpacing(true);
        
        buttonAddWrong.addClickListener(b -> addWrongOrder());
        buttonRemoveAllOrders.addClickListener(b -> removeAllOrders());
        buttonAdd10Orders.addClickListener(b -> add10Orders());
        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAllOrders,buttonAdd10Orders,buttonAddWrong);
        buttons.setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        add(buttons);
        add(grid);
        reload();
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
