package test.graphs;

import core.graphs.TarjanSCC;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Hieu Le
 * @version 3/13/18
 */
public class TarjanSCCTest {
    @Test
    public void getSCCOnCyclicGraph() throws Exception {
        List<Integer>[] graph = new List[3];
        graph[0] = Collections.singletonList(1);
        graph[1] = Collections.singletonList(2);
        graph[2] = Collections.singletonList(0);

        List<List<Integer>> components = TarjanSCC.getSCC(graph);
        assertEquals(components.size(), 1);
        assertEquals(components.get(0).size(), 3);
    }

    @Test
    public void getSCCOnAcyclicGraph() throws Exception {
        List<Integer>[] graph = new List[3];
        graph[0] = Collections.singletonList(1);
        graph[1] = Collections.singletonList(2);
        graph[2] = Collections.emptyList();

        List<List<Integer>> components = TarjanSCC.getSCC(graph);
        assertEquals(components.size(), 3);
        for (List<Integer> component : components)
            assertEquals(component.size(), 1);
    }

    @Test
    public void getSCCOnNormalGraph() throws Exception {
        List<Integer>[] graph = new List[3];
        graph[0] = Collections.singletonList(1);
        graph[1] = Collections.singletonList(0);
        graph[2] = Collections.singletonList(1);

        List<List<Integer>> components = TarjanSCC.getSCC(graph);
        assertEquals(components.size(), 2);
    }
}