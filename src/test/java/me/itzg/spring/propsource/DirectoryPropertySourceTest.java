package me.itzg.spring.propsource;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Geoff Bourne
 * @since Oct 2017
 */
public class DirectoryPropertySourceTest {

    @Test
    public void testNormal() {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("greeting");
        assertEquals("hello", value);
    }

    @Test
    public void testMissingProp() {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("nothere");
        assertNull(value);

    }

    @Test
    public void testMissingSourceDir() {
        final DirectoryPropertySource source =
                new DirectoryPropertySource("testing",
                                            Paths.get("src/test/resources/nothere"));

        final Object value = source.getProperty("blah");
        assertNull(value);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongTypeOfSource() {
        new DirectoryPropertySource("testing",
                                    Paths.get("src/test/resources/props/greeting"));

    }

    @Test
    public void testImproperPropName() {
        final DirectoryPropertySource propertySource =
                new DirectoryPropertySource("testing",
                                            Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("/etc/password");
        assertNull("Should have disallowed absolute paths", value);
    }

    @Test
    public void testDottedName() {
        final DirectoryPropertySource propertySource =
                new DirectoryPropertySource("testing",
                                            Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("grouped.prize");
        assertEquals("nothing", value);
    }

    @Test
    public void testTrimsWhitespace() {
        final DirectoryPropertySource propertySource = new DirectoryPropertySource("testing",
                                                                                   Paths.get("src/test/resources/props"));

        final Object value = propertySource.getProperty("extraspace");
        assertEquals("ignore white space", value);

    }
}