package me.itzg.spring.propsource;

import me.itzg.spring.propsource.dockersecrets.DockerSecretsPropConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When annotating a {@link Configuration} class, this will enable a Spring property source that
 * loads files named for the property in <code>/run/secrets</code> into the property's value.
 * @author Geoff Bourne
 * @since Jan 2018
 */
@SuppressWarnings("WeakerAccess")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DockerSecretsPropConfig.class)
public @interface EnableDockerSecretsPropertySource {
}
