package me.itzg.spring.propsource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Geoff Bourne
 * @since Jan 2018
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestPropertySource(properties = "docker.secrets.path=/${user.dir}/src/test/resources/props")
public class EnableDockerSecretsPropertySourceTest {
    @Configuration
    @EnableDockerSecretsPropertySource
    static class Config {

    }

    @Autowired
    Environment env;

    @Test
    public void testPropViaEnv() throws Exception {
        final String value = env.getProperty("greeting");
        assertEquals("hello", value);
    }
}
