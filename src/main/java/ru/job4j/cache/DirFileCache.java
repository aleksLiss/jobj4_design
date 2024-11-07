package ru.job4j.cache;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws FileNotFoundException {
        Path path = Paths.get(new StringBuilder(cachingDir)
                .append("/")
                .append(key)
                .toString());
        String result = null;
        if (Files.notExists(path)) {
            throw new FileNotFoundException("This file is not exist!");
        }
        if (Files.isRegularFile(path)) {
            try {
                result = Files.readString(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}