package fr.m2iformation.departements;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class DepartementTest {
    public static String regex = "[0-9]{2}|2A|2B|97[0-5]";



    @Test
    public void isValid() {

        boolean match;
        match = Pattern.matches(regex,"2A");
        assertEquals(true, match);

        match = Pattern.matches(regex,"2B");
        assertEquals(true, match);

        match = Pattern.matches(regex,"2C");
        assertEquals(false, match);

        match = Pattern.matches(regex,"AA");
        assertEquals(false, match);

        match = Pattern.matches(regex,"100");
        assertEquals(false, match);

        match = Pattern.matches(regex,"234");
        assertEquals(false, match);


        match = Pattern.matches(regex,"3224");
        assertEquals(false, match);

        match = Pattern.matches(regex,"972");
        assertEquals(true, match);

        match = Pattern.matches(regex,"978");
        assertEquals(false, match);

    }
}
