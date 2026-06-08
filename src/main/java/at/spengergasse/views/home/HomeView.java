package at.spengergasse.views.home;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getHeader());

        Image img = new Image("images/logo.png", "Mochi Cafe Logo");
        img.setWidth("350px");
        img.setHeight("350px");

        Paragraph description = new Paragraph(
                "In unserem Mochi Café verbinden wir aromatischen Kaffee aus frisch gerösteten Kaffeebohnen mit den süßen Spezialitäten der philippinischen Dessertkultur. Jede Tasse wird mit Sorgfalt zubereitet, um den vollen Geschmack unserer hochwertigen Bohnen zu entfalten. Von traditionellen Leckereien bis hin zu modernen Mochi-Kreationen – bei uns stehen Qualität, Genuss und ein einzigartiges Café-Erlebnis im Mittelpunkt.\n");
        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "middle");

        H3 name = new H3("Mochi Cafe GmbH");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");


        HorizontalLayout desc = new HorizontalLayout(img, description);
        add(desc);

        HorizontalLayout adress = new HorizontalLayout(name, street, city);
        adress.getStyle().set("gap", "40px");
        add(adress);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    public static Component getHeader(){
        VerticalLayout header;

        H1 companyName = new H1("Mochi Cafe");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... the only coffee you need...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");
        header = new VerticalLayout(companyName, subName);
        header.setSpacing(false);
        header.setPadding(false);
        header.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        return header;
    }

}
