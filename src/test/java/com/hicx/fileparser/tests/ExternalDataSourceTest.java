package com.hicx.fileparser.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import com.hicx.fileparser.datasource.ExternalDataSource;
import org.junit.Test;
import org.junit.jupiter.api.Test;

public class ExternalDataSourceTest {
    @Test
    public void testHasNextReturnsTrueForValidURL() {
        ExternalDataSource dataSource = new ExternalDataSource("https://www.example.com");
        assertTrue(dataSource.hasNext());
    }

    @Test
    public void testHasNextReturnsFalseForInvalidURL() {
        ExternalDataSource dataSource = new ExternalDataSource("https://www.invalid-example.com");
        assertFalse(dataSource.hasNext());
    }

    @Test
    public void testNextReturnsDataForValidURL() {
        ExternalDataSource dataSource = new ExternalDataSource("https://www.example.com");
        byte[] data = dataSource.next();
        assertNotNull(data);
        assertTrue(data.length > 0);
    }

    @Test
    public void testNextReturnsEmptyArrayForInvalidURL() {
        ExternalDataSource dataSource = new ExternalDataSource("https://www.invalid-example.com");
        byte[] data = dataSource.next();
        assertNotNull(data);
        assertEquals(0, data.length);
    }
}

