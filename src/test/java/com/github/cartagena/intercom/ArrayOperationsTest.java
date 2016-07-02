package com.github.cartagena.intercom;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ArrayOperationsTest {

    private ArrayOperations operations = new ArrayOperations();

    @Test
    public void shouldFlattenSimpleNestedArray() {
        Object[] nestedArray = new Object[] {
            1,
            new Object[] { 2, 3 }
        };

        Integer[] flattenedArray = operations.flatIntegerArray(nestedArray);

        assertThat(flattenedArray)
                .hasSize(3)
                .containsExactly(1, 2, 3);

    }

    @Test
    public void shouldFlattenDoubleNestedArray() {
        Object[] nestedArray = new Object[] {
                1,
                new Object[]{ 2, 3 },
                new Object[]{
                        new Object[]{ 4 },
                        5, 6}
        };

        Integer[] flattenedArray = operations.flatIntegerArray(nestedArray);

        assertThat(flattenedArray)
                .hasSize(6)
                .containsExactly(1, 2, 3, 4, 5, 6);

    }

    @Test
    public void shouldFlattenSimpleDeepNestedArray() {
        Object[] nestedArray = new Object[] {
                1,
                new Object[]{
                        new Object[]{
                                new Object[]{
                                        new Object[]{
                                                new Object[]{
                                                        new Object[]{
                                                                new Object[]{
                                                                        new Object[]{
                                                                                new Object[]{
                                                                                        new Object[]{
                                                                                                new Object[]{
                                                                                                        new Object[]{2}
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        },
                }
        };

        Integer[] flattenedArray = operations.flatIntegerArray(nestedArray);

        assertThat(flattenedArray)
                .hasSize(2)
                .containsExactly(1, 2);

    }

    @Test
    public void shouldThrowExceptionIfNestedArrayDoesNotContainOnlyIntegers() {
        Object[] nestedArray = new Object[] {
                1,
                new Object[] { 2, "String" }
        };

        try {
            operations.flatIntegerArray(nestedArray);
            fail("Expecting exception when argument is not composed only by integers.");
        } catch(Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Argument should contain only integers or array of integers.");
        }

    }

    @Test
    public void shouldThrowExceptionIfInputArrayDoesNotContainOnlyIntegers() {
        Object[] nestedArray = new Object[] {
                1,
                "String"
        };

        try {
            operations.flatIntegerArray(nestedArray);
            fail("Expecting exception when argument is not composed only by integers or nested array of integers.");
        } catch(Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Argument should contain only integers or array of integers.");
        }

    }

    @Test
    public void shouldReturnEmptyArrayIfInputIsEmpty() {
        Integer[] flattenedArray = operations.flatIntegerArray(new Object[0]);

        assertThat(flattenedArray)
                .isEmpty();

    }

    @Test
    public void shouldThrowExceptionIfInputArrayIsNull() {
        try {
            operations.flatIntegerArray(null);
            fail("Expecting exception when argument is null.");
        } catch(Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Argument should not be null.");
        }

    }

    @Test
    public void shouldThrowExceptionIfNestedElementIsNull() {
        Object[] nestedArray = new Object[] {
                1,
                new Object[] { null }
        };

        try {
            operations.flatIntegerArray(nestedArray);
            fail("Expecting exception when argument is not composed only by integers or nested array of integers.");
        } catch(Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Argument should contain only integers or array of integers.");
        }
    }

    @Test
    public void shouldFlattenSimpleNestedArrayWithEmptyNestedArray() {
        Object[] nestedArray = new Object[] {
                1,
                new Object[] { 2, 3 },
                new Object[0]
        };

        Integer[] flattenedArray = operations.flatIntegerArray(nestedArray);

        assertThat(flattenedArray)
                .hasSize(3)
                .containsExactly(1, 2, 3);

    }
}
