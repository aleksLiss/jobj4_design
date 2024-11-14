package ru.job4j.ood.srp.formatter;

import java.nio.file.Files;
import java.nio.file.Path;

public class PathCheck implements PathChecker {
    @Override
    public boolean check(String path) {
        Path pathToFile = Path.of(path);
        if (!Files.isReadable(pathToFile)) {
            return false;
        }
        if (Files.notExists(pathToFile)) {
            return false;
        }
        return true;
    }
}
