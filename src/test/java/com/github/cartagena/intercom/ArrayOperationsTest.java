package com.github.cartagena.intercom;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayOperationsTest {

    @Test
    public void shouldFlattenSimpleNestedArray() {
        Object[] nestedArray = new Object[2];
        nestedArray[0] = 1;
        nestedArray[1] = new Object[] { 2, 3 };

        ArrayOperations operations = new ArrayOperations();
        int[] flattedArray = operations.flatArray(nestedArray);

        assertThat(flattedArray)
                .hasSize(3)
                .containsExactly(1, 2, 3);

    }

}
