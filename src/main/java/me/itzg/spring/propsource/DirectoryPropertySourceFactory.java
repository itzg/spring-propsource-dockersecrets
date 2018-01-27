package me.itzg.spring.propsource;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This factory can be used for {@link org.springframework.context.annotation.PropertySource#factory()}
 * to instantiate a {@link DirectoryPropertySource} instance.
 *
 * @author Geoff Bourne
 * @since Oct 2017
 */
public class DirectoryPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name,
                                                  EncodedResource encodedResource) throws IOException {

        final Path path = Paths.get(encodedResource.getResource().getURI());

        final DirectoryPropertySource propertySource = new DirectoryPropertySource(name, path);
        final Charset charset = encodedResource.getCharset();
        //noinspection ConstantConditions
        if (charset != null) {
            propertySource.setCharset(charset);
        }

        return propertySource;
    }
}
