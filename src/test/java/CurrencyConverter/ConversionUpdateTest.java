package CurrencyConverter;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConversionUpdateTest {


    @Test void testCreation() { // Test that the update class returns *something*
        ConversionUpdate update = new ConversionUpdate(new Date(), 0.0f);
        assertNotNull(update, "Conversion update should generate");
    }

    @Test void testDate() { // Make sure the date is correct

        Date current = new Date();

        ConversionUpdate update = new ConversionUpdate(current, 0.0f);
        assertEquals(update.getDate().getTime(), current.getTime(), "Conversion update should return correct date");
    }

    @Test void testValue() { // Ensure that the conversion update stores the correct value

        float value = 1.7f;

        ConversionUpdate update = new ConversionUpdate(new Date(), value);
        assertEquals(update.getValue(), value, "Conversion update should return correct value");
    }

    @Test void testValueRounding() { // Ensure that the conversion update rounds automatically

        float value = 1.785f;

        ConversionUpdate update = new ConversionUpdate(new Date(), value);
        assertEquals(update.getValue(), 1.79f, "Conversion update should round to 2 d.p");
    }
}
