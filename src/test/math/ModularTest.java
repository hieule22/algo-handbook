package test.math;

import core.math.Modular;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hieu Le
 * @version 11/3/16
 */
public class ModularTest {

    @Test
    public void testMod() throws Exception {
        assertEquals(Modular.mod(7, 2), 1);
        assertEquals(Modular.mod(6, 2), 0);
        assertEquals(Modular.mod(0, 2), 0);
        assertEquals(Modular.mod(9, 12), 9);
        assertEquals(Modular.mod(12, 9), 3);
        assertEquals(Modular.mod(-1, 2), 1);
        assertEquals(Modular.mod(-5, 9), 4);
        assertEquals(Modular.mod(Integer.MAX_VALUE, Integer.MAX_VALUE), 0);
    }

    @Test
    public void testGcd() throws Exception {
        assertEquals(Modular.gcd(6, 9), 3);
        assertEquals(Modular.gcd(9, 6), 3);
        assertEquals(Modular.gcd(1, 1), 1);
        assertEquals(Modular.gcd(3, 0), 3);
        assertEquals(Modular.gcd(0, 3), 3);
        assertEquals(Modular.gcd(Integer.MAX_VALUE, Integer.MAX_VALUE), Integer.MAX_VALUE);
    }

    @Test
    public void testLcm() throws Exception {
        assertEquals(Modular.lcm(6, 9), 18);
        assertEquals(Modular.lcm(3, 3), 3);
        assertEquals(Modular.lcm(Integer.MAX_VALUE, Integer.MAX_VALUE), Integer.MAX_VALUE);
    }
}