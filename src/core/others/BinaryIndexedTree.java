package core.others;

/**
 * Binary Indexed Tree (aka Fenwick Tree) is a data structure useful for implementing dynamic
 * frequency tables.
 * @author Hieu Le
 * @version 10/27/16
 */
public class BinaryIndexedTree {

    private int capacity;
    private int[] data;

    // BIT can be thought of as having entries f[1], ..., f[n] which are 0-initialized.
    public BinaryIndexedTree(int capacity) {
        this.capacity = capacity;
        data = new int[capacity + 1];
    }

    // Returns f[1] + f[2] + ... + f[index]
    public int read(int index) {
        int result = 0;
        while (index > 0) {
            result += data[index];
            index -= (index & -index);
        }
        return result;
    }

    // Returns f[low] + ... + f[high]
    public int read(int low, int high) {
        return read(high) - read(low - 1);
    }

    // Adds value to f[index]
    public void update(int index, int value) {
        while (index <= capacity) {
            data[index] += value;
            index += (index & -index);
        }
    }
}
