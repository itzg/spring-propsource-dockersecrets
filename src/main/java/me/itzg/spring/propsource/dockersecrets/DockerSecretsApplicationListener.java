package me.itzg.spring.propsource.dockersecrets;

import me.itzg.spring.propsource.DirectoryPropertySource;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Intercepts early in the Spring Boot context setup to ensure the Docker secrets are available as
 * a property source before logging is setup.
 *
 * @author Geoff Bourne
 * @since Jan 2018
 */
@SuppressWarnings("unused")
public class DockerSecretsApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        final ConfigurableEnvironment env = event.getEnvironment();

        final Path dockerSecretsPath = Paths.get(env.getProperty("docker.secrets.path", "/run/secrets"));
        env.getPropertySources().addLast(
                new DirectoryPropertySource("dockerSecrets",
                                            dockerSecretsPath)
        );

    }
}
