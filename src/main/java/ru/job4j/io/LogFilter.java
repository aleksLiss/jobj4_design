package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;
    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(("data/log.txt")))) {
            reader.lines()
                    .map(str -> str.split(" "))
                    .filter(strings -> strings[strings.length - 2].contains("404"))
                    .map(i -> String.join(" ", i))
                    .forEach(i -> result.add((i + System.lineSeparator())));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            data.stream()
                    .forEach(writer::print);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
