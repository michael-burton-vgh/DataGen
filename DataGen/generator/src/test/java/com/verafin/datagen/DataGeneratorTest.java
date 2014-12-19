package com.verafin.datagen;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

public class DataGeneratorTest {

    @Before
    public void setUp()
    {

    }

    @Test
    public void test_generateSingleFieldSingleRow()
    {
        List<FieldDefinition> fields = new ArrayList<FieldDefinition>();
        fields.add(new FieldDefinition("field", 10));

        DataGenerator sut = new DataGenerator(fields);
        String out = sut.generate(1);
        assertThat(out, matchesPattern("field\\n[a-zA-z0-9]{1,10}"));
    }

    @Test
    public void test_generateMultipleFieldsSingleRow()
    {
        List<FieldDefinition> fields = new ArrayList<FieldDefinition>();

        fields.add(new FieldDefinition("field", 10));
        fields.add(new FieldDefinition("field2", 10));
        DataGenerator sut = new DataGenerator(fields);
        String out = sut.generate(1);
        assertThat(out, matchesPattern("field,field2\\n[a-zA-Z0-9]{1,10},[a-zA-Z0-9]{1,10}"));
    }

    @Test
    public void test_generateMultipleRows()
    {
        List<FieldDefinition> fields = new ArrayList<FieldDefinition>();

        fields.add(new FieldDefinition("field", 10));
        fields.add(new FieldDefinition("field2", 10));

        DataGenerator sut = new DataGenerator(fields);

        String out = sut.generate(3);
        assertThat(out, matchesPattern("field,field2\\n[a-zA-Z0-9]{1,10},[a-zA-Z0-9]{1,10}\\n[a-zA-Z0-9]{1,10},[a-zA-Z0-9]{1,10}\\n[a-zA-Z0-9]{1,10},[a-zA-Z0-9]{1,10}"));
    }

    @Test
    public void test_maxRowsWithoutMemoryIssues()
    {
        List<FieldDefinition> fields = new ArrayList<FieldDefinition>();

        // Changing the number and length of fields and rows will affect
        // whether this test passes or not. It's basically a tuning harness
        // for the DataGenerator class.
        fields.add(new FieldDefinition("field", 10));
        fields.add(new FieldDefinition("field2", 10));
        fields.add(new FieldDefinition("field3", 10));
        fields.add(new FieldDefinition("field4", 10));
        fields.add(new FieldDefinition("field5", 10));
        fields.add(new FieldDefinition("field6", 10));
        fields.add(new FieldDefinition("field7", 10));
        fields.add(new FieldDefinition("field8", 10));
        fields.add(new FieldDefinition("field9", 10));
        fields.add(new FieldDefinition("field10", 10));
        fields.add(new FieldDefinition("field11", 10));
        fields.add(new FieldDefinition("field12", 10));
        DataGenerator sut = new DataGenerator(fields);

        @SuppressWarnings("unused")
        String out = sut.generate(5000000);
    }
}
