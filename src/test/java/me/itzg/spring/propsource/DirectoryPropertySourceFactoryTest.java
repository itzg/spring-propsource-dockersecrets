package me.itzg.spring.propsource;

import me.itzg.spring.propsource.DirectoryPropertySourceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Geoff Bourne
 * @since Oct 2017
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
public class DirectoryPropertySourceFactoryTest {

    @Configuration
    @PropertySource(
            name = "testing",
            value = "file:///${user.dir}/src/test/resources/props",
            factory = DirectoryPropertySourceFactory.class)
    static class Config {

    }

    @Autowired
    Environment env;

    @Test
    public void testPropViaEnc() throws Exception {
        final String value = env.getProperty("greeting");
        assertEquals("hello", value);
    }
}