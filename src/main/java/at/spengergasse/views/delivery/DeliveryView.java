package at.spengergasse.views.delivery;

import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.OptionalDouble;

@PageTitle("Delivery")
@Route("delivery")
@Menu(order = 2, icon = LineAwesomeIconUrl.CAR_SIDE_SOLID)
public class DeliveryView extends VerticalLayout {

    public DeliveryView() {
        setSpacing(false);

        add(HomeView.getHeader());

        H2 title = new H2("Lieferung");
        add(title);

        Component zone1 = getCard("5. Bezirk", 3.9, OptionalDouble.of(15.0));
        Component zone2 = getCard("3. und 4. Bezirk", 5.9, OptionalDouble.of(20));
        Component zone3 = getCard("Wien", 7.9, OptionalDouble.of(30));
        Component zone4 = getCard("Außerhalb von Wien", 20, OptionalDouble.empty());

        FlexLayout cardsLayout = new FlexLayout(zone1, zone2, zone3, zone4);
        cardsLayout.setWidthFull();
        cardsLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        cardsLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(cardsLayout);

        Paragraph info = new Paragraph("Wir liefern Ihre Bestellung in der Regel innerhalb von 30 bis 90 Minuten – frisch und heiß zu Ihnen nach Hause.");
        info.setWidth("100%");
        info.getStyle().set("text-align", "center");
        add(info);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private Component getCard(String sellingZone, double deliveryPrice, OptionalDouble freeDelivery){
        Paragraph free;

        H2 zone = new H2(sellingZone);
        Paragraph price = new Paragraph(deliveryPrice + " Euro");
        if(freeDelivery.isPresent()){
            free = new Paragraph( "Gratislieferung " + freeDelivery.getAsDouble() + " Euro");
        }else{
            free = new Paragraph("Keine Gratislieferung");
        }
        VerticalLayout card = new VerticalLayout(zone, price, free);
        card.setWidth("350px");
        card.setPadding(true);
        card.setSpacing(false);

        card.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px");

        return card;
    }

}
