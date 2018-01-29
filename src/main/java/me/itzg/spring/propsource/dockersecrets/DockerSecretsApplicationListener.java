package me.itzg.spring.propsource.dockersecrets;

import me.itzg.spring.propsource.DirectoryPropertySource;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
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
public class DockerSecretsApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    public static final int DEFAULT_ORDER = LoggingApplicationListener.DEFAULT_ORDER-1;

    private int order = DEFAULT_ORDER;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        final ConfigurableEnvironment env = event.getEnvironment();

        final Path dockerSecretsPath = Paths.get(env.getProperty("docker.secrets.path", "/run/secrets"));
        env.getPropertySources().addLast(
                new DirectoryPropertySource("dockerSecrets",
                                            dockerSecretsPath)
        );

    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
