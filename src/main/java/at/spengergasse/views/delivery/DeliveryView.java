package at.spengergasse.views.delivery;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Delivery")
@Route("delivery")
@Menu(order = 2, icon = LineAwesomeIconUrl.CAR_SIDE_SOLID)
public class DeliveryView extends VerticalLayout {

    public DeliveryView() {
        setSpacing(false);
        H1 companyName = new H1("Mochi Cafe");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... the only coffee you need ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        H2 title = new H2("Lieferung");

        H2 zone1 = new H2("5. Bezirk");
        Paragraph price1 = new Paragraph("Lieferkosten: 3,90 Euro");
        Paragraph free1 = new Paragraph("Gratislieferung ab 15,00 Euro");

        H2 zone2 = new H2("3. und 4. Bezirk");
        Paragraph price2 = new Paragraph("Lieferkosten: 5,90 Euro");
        Paragraph free2 = new Paragraph("Gratislieferung ab 20,00 Euro");

        H2 zone3 = new H2("Wien");
        Paragraph price3 = new Paragraph("Lieferkosten: 7,90 Euro");
        Paragraph free3 = new Paragraph("Gratislieferung ab 30,00 Euro");

        H2 zone4 = new H2("Außerhalb von Wien");
        Paragraph price4 = new Paragraph("Lieferkosten: 20,00 Euro");
        Paragraph free4 = new Paragraph("Keine Gratislieferung");

        add(
                companyName, subName, title,
                zone1, price1, free1,
                zone2, price2, free2,
                zone3, price3, free3,
                zone4, price4, free4
        );

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
