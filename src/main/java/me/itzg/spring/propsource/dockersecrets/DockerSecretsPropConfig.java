package me.itzg.spring.propsource.dockersecrets;

import me.itzg.spring.propsource.DirectoryPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * This configuration bean enables <a href="https://docs.docker.com/engine/swarm/secrets/">Docker Secrets</a>
 * to become available as regular {@link org.springframework.core.env.Environment} entries.
 *
 * @author Geoff Bourne
 * @since Oct 2017
 */
@Configuration
@PropertySource(
        name = "docker-secrets",
        value = "file:///run/secrets",
        factory = DirectoryPropertySourceFactory.class)
public class DockerSecretsPropConfig {
}
