package at.spengergasse.domain;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.vaadin.copilot.shaded.helger.base.system.CSystemProperty;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test

    public void testToString(){
        Order a = new Order(LocalDate.now(), "Cappucino", "Venti", 5.0, 1,true);
        System.out.println(a);
        System.out.println(a.getPrice());
        assertEquals(a.getPrice(),5.0);
    }


}