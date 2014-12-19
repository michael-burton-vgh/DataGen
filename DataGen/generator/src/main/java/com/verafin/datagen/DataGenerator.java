package com.verafin.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    final List<FieldDefinition> fields;
    List<Character> charSet;

    public DataGenerator(List<FieldDefinition> fields) {
        this.fields = new ArrayList<FieldDefinition>(fields);
        generateCharSet();
    }

    private void generateCharSet() {
        charSet = new ArrayList<Character>();
        char[] charBounds = new char[] { '0', '9', 'a', 'z', 'A', 'Z'};
        for(int i = 0; i < charBounds.length; i+=2)
        {
            for(char c=charBounds[i]; c <= charBounds[i+1]; c++)
            {
                charSet.add(c);
            }
        }
    }

    public String generate(int rowCount) {
        StringBuilder dataBuilder = new StringBuilder();
        addHeaders(dataBuilder);
        for (int row = 0; row < rowCount; row++) {
            dataBuilder.append("\n");
            addRow(dataBuilder);
        }
        return dataBuilder.toString();
    }

    private void addHeaders(StringBuilder dataBuilder) {
        for (FieldDefinition field : fields) {
            dataBuilder.append(field.name);
            dataBuilder.append(",");
        }
        dataBuilder.delete(dataBuilder.length() - 1, dataBuilder.length());
    }

    private void addRow(StringBuilder dataBuilder) {
        for (FieldDefinition field : fields) {
            addValue(dataBuilder, field);
            dataBuilder.append(",");
        }
        dataBuilder.delete(dataBuilder.length() - 1, dataBuilder.length());
    }

    private void addValue(StringBuilder dataBuilder, FieldDefinition field) {
        long genLen = (long)(Math.random() * field.length) + 1;
        for(int i = 0; i < genLen; i++)
        {
            dataBuilder.append(generateCharacter());
        }
    }

    private Character generateCharacter() {
        Random rand = new Random();
        return charSet.get(rand.nextInt(charSet.size()));
    }
}
