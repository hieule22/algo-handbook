package test.others;

import core.others.BinaryIndexedTree;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hieu Le
 * @version 10/27/16
 */
public class BinaryIndexedTreeTest {
    private static final int CAPACITY = 1000;
    private static final int MAX_VALUE = 1000;

    @Test
    public void testBITBasic() throws Exception {
        BinaryIndexedTree actual = new BinaryIndexedTree(CAPACITY);
        BinaryIndexedTree expected = new BITNaive(CAPACITY);
        Random rng = new Random(System.currentTimeMillis());

        for (int i = 1; i <= CAPACITY; ++i) {
            int value = rng.nextInt(MAX_VALUE);
            int index = rng.nextInt(CAPACITY) + 1;
            actual.update(index, value);
            expected.update(index, value);
        }

        for (int i = 0; i < 1000; ++i) {
            // Read a prefix sum of values.
            int index = rng.nextInt(CAPACITY) + 1;
            assertEquals(expected.read(index), actual.read(index));

            // Read a segment sum of values.
            int low = rng.nextInt(CAPACITY) + 1;
            int high = rng.nextInt(CAPACITY) + 1;
            if (low > high) {
                int temp = low;
                low = high;
                high = temp;
            }
            assertEquals(expected.read(low, high), actual.read(low, high));

            // Update an indexed element.
            index = rng.nextInt(CAPACITY) + 1;
            int value = rng.nextInt(MAX_VALUE);
            expected.update(index, value);
            actual.update(index, value);
        }
    }

    private class BITNaive extends BinaryIndexedTree {

        private int[] data;

        public BITNaive(int capacity) {
            super(capacity);
            this.data = new int[capacity + 1];
        }

        @Override
        public int read(int index) {
            int result = 0;
            for (int i = 1; i <= index; ++i)
                result += data[i];
            return result;
        }

        @Override
        public int read(int low, int high) {
            int result = 0;
            for (int i = low; i <= high; ++i)
                result += data[i];
            return result;
        }

        @Override
        public void update(int index, int value) {
            data[index] += value;
        }
    }
}