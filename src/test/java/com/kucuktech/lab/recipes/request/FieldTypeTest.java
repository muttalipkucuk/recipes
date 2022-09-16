package com.kucuktech.lab.recipes.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTypeTest {

    @Test
    void testParseBoolean() {
        assertTrue((boolean) FieldType.BOOLEAN.parse("true"));
        assertTrue((boolean) FieldType.BOOLEAN.parse("TRUE"));
        assertFalse((boolean) FieldType.BOOLEAN.parse("false"));
        assertFalse((boolean) FieldType.BOOLEAN.parse("FALSE"));
        assertFalse((boolean) FieldType.BOOLEAN.parse("1"));
        assertFalse((boolean) FieldType.BOOLEAN.parse("0"));
        assertFalse((boolean) FieldType.BOOLEAN.parse(""));
        assertFalse((boolean) FieldType.BOOLEAN.parse(null));
    }

    @Test
    void testParseInteger() {
        assertEquals(1, FieldType.INTEGER.parse("1"));
        assertEquals(0, FieldType.INTEGER.parse("0"));
        assertEquals(-1, FieldType.INTEGER.parse("-1"));
        assertThrows(NumberFormatException.class, () -> FieldType.INTEGER.parse("1.0"));
        assertThrows(NumberFormatException.class, () -> FieldType.INTEGER.parse(""));
        assertThrows(NumberFormatException.class, () -> FieldType.INTEGER.parse(null));
    }

    @Test
    void testParseString() {
        assertEquals("foo", FieldType.STRING.parse("foo"));
        assertEquals("", FieldType.STRING.parse(""));
        assertNull(FieldType.STRING.parse(null));
    }
}
