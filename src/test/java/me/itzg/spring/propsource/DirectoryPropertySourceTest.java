package me.itzg.spring.propsource;

import me.itzg.spring.propsource.DirectoryPropertySource;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * @author Geoff Bourne
 * @since Oct 2017
 */
public class DirectoryPropertySourceTest {

    @Test
    public void testNormal() throws Exception {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("greeting");
        assertEquals("hello", value);
    }

    @Test
    public void testMissingProp() throws Exception {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("nothere");
        assertNull(value);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingSourceDir() throws Exception {
        new DirectoryPropertySource("testing",
                                    Paths.get("src/test/resources/nothere"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongTypeOfSource() throws Exception {
        new DirectoryPropertySource("testing",
                                    Paths.get("src/test/resources/props/greeting"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testImproperPropName() throws Exception {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        propertySource.getProperty("/etc/passwod");
    }

    @Test
    public void testDottedName() throws Exception {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("grouped.prize");
        assertEquals("nothing", value);
    }

    @Test
    public void testTrimsWhitespace() throws Exception {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("extraspace");
        assertEquals("ignore white space", value);

    }
}