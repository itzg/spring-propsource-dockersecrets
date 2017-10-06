package me.itzg.spring.propsource;

import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Geoff Bourne
 * @since Oct 2017
 */
public class DirectoryPropertySource extends PropertySource<Path> {

    private Charset charset = StandardCharsets.UTF_8;

    public DirectoryPropertySource(String name, Path source) {
        super(name, source);

        if (!Files.isDirectory(source)) {
            throw new IllegalArgumentException("source needs to be a directory");
        }
    }

    public DirectoryPropertySource(String name) {
        super(name);
        throw new IllegalStateException("A source path is required");
    }

    /**
     * Set the character set used for reading the property-content file.
     * Default is {@link StandardCharsets#UTF_8}
     * @param charset the character set to use
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    @Nullable
    public Object getProperty(String name) {
        final Path childPath = Paths.get(name);
        if (childPath.getNameCount() > 1) {
            throw new IllegalArgumentException("name cannot contain path delimiters");
        }
        if (childPath.isAbsolute()) {
            throw new IllegalArgumentException("name cannot be absolute");
        }

        Path pathOfProp = getSource();
        final String[] nameParts = name.split("\\.");
        for (String namePart : nameParts) {
            pathOfProp = pathOfProp.resolve(namePart);
        }

        try {
            final byte[] content = Files.readAllBytes(pathOfProp);
            return new String(content, charset).trim();
        } catch (IOException e) {
            return null;
        }
    }
}
