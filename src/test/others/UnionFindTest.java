package test.others;

import core.others.UnionFind;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hieu Le
 * @version 10/27/16
 */
public class UnionFindTest {
    private static final int N = 1000;

    @Test
    public void testUnionFindBasic() throws Exception {
        UnionFind actual = new UnionFind(N);
        UnionFind expected = new UnionFindNaive(N);
        Random rng = new Random(System.currentTimeMillis());

        for (int i = 0; i < 1000; ++i) {
            int a = rng.nextInt(N);
            int b = rng.nextInt(N);

            assertEquals(actual.isSameSet(a, b), expected.isSameSet(a, b));
            actual.unionSet(a, b);
            expected.unionSet(a, b);
        }
    }

    private class UnionFindNaive extends UnionFind {
        private List<Set<Integer>> disjointSets;

        public UnionFindNaive(int n) {
            super(n);
            disjointSets = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                Set<Integer> singletonSet = new HashSet<>();
                singletonSet.add(i);
                disjointSets.add(singletonSet);
            }
        }

        @Override
        protected int findSet(int node) {
            for (int i = 0; i < disjointSets.size(); ++i) {
                if (disjointSets.get(i).contains(node))
                    return i;
            }
            throw new RuntimeException();
        }

        @Override
        public boolean isSameSet(int a, int b) {
            return findSet(a) == findSet(b);
        }

        @Override
        public void unionSet(int a, int b) {
            int indexA = findSet(a);
            int indexB = findSet(b);

            if (indexA != indexB) {
                disjointSets.get(indexA).addAll(disjointSets.get(indexB));
                disjointSets.remove(indexB);
            }
        }
    }

}