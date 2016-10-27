package test.others;

import core.others.SegmentTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hieu Le
 * @version 10/27/16
 */
public class SegmentTreeTest {

    @Test
    public void testSegmentTree() throws Exception {
        final int LENGTH = 1000;
        SegmentTree actual = new SegmentTree(LENGTH);
        SegmentTree expected = new NaiveSegmentTree(LENGTH);

        Random rng = new Random(System.currentTimeMillis());
        for (int i = 0; i < LENGTH; ++i) {
            int value = rng.nextInt();
            actual.update(i, value);
            expected.update(i, value);
        }

        final int N_QUERIES = 1000;
        for (int i = 0; i < N_QUERIES; ++i) {
            int position = rng.nextInt(LENGTH);
            int value = rng.nextInt();
            actual.update(position, value);
            expected.update(position, value);

            int low = rng.nextInt(LENGTH);
            int high = rng.nextInt(LENGTH);
            if (low >= high) {
                int temp = low;
                low = high;
                high = temp;
            }
            assertEquals(actual.query(low, high), expected.query(low, high));
        }
    }

    private class NaiveSegmentTree extends SegmentTree {

        private int[] data;

        /**
         * Constructs a segment tree for a given range.
         * @param range the number of elements in the array.
         */
        public NaiveSegmentTree(int range) {
            super(range);
            data = new int[range];
            Arrays.fill(data, Integer.MIN_VALUE);
        }

        @Override
        public void update(int position, int value) {
            data[position] = value;
        }

        @Override
        public int query(int low, int high) {
            int result = Integer.MIN_VALUE;
            for (int i = low; i <= high; ++i) {
                result = Math.max(result, data[i]);
            }
            return result;
        }
    }
}