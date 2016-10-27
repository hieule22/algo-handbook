package core.others;

import java.util.Arrays;

/**
 * Implementation of a maximum segment tree.
 * @author Hieu Le
 * @version 10/27/16
 */
public class SegmentTree {
    private static final int SPAN_FACTOR = 4;

    private final int[] tree;
    private final int range;

    /**
     * Constructs a segment tree for a given range.
     * @param range the number of elements in the array.
     */
    public SegmentTree(int range) {
        if (range < 0) {
            throw new IllegalArgumentException("Range cannot be negative: " + range);
        }
        this.range = range;
        tree = new int[range * SPAN_FACTOR];
        Arrays.fill(tree, Integer.MIN_VALUE);
    }

    /**
     * Updates element at a given position to a given value.
     * @param position the index of the element to update
     * @param value the new value
     */
    public void update(int position, int value) {
        if (position < 0 || position >= range) {
            throw new IndexOutOfBoundsException("Invalid update index: " + position);
        }
        updateImpl(0, 0, range - 1 ,position, value);
    }

    private void updateImpl(int index, int begin, int end, int position, int value) {
        if (position < begin || end < position) {
            return;
        }
        if (begin == end) {
            tree[index] = value;
            return;
        }

        int mid = begin + (end - begin) / 2;
        updateImpl(2 * index + 1, begin, mid, position, value);
        updateImpl(2 * index + 2, mid + 1, end, position, value);
        tree[index] = Math.max(tree[2 * index + 1], tree[2 * index + 2]);
    }

    /**
     * Returns the maximum element between low and high (inclusively).
     * @param low the lower bound of the segment
     * @param high the upper bound of the segment
     * @return the value of the greatest element in that segment
     */
    public int query(int low, int high) {
        if (low < 0 || high >= range || low > high) {
            throw new IllegalArgumentException(
                    String.format("Illegal range query: %d %d", low, high));
        }
        return queryImpl(0, 0, range - 1, low, high);
    }

    private int queryImpl(int index, int begin, int end, int left, int right) {
        if (end < left || right < begin) {
            return Integer.MIN_VALUE;
        }
        if (left <= begin && end <= right) {
            return tree[index];
        }

        int mid = begin + (end - begin) / 2;
        int leftHalf = queryImpl(2 * index + 1, begin, mid, left, right);
        int rightHalf = queryImpl(2 * index + 2, mid + 1, end, left, right);
        return Math.max(leftHalf, rightHalf);
    }
}
