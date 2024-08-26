package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void getTimeWhenServerWasDownTwoTimes(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat(result.toString()).contains("log10:57:01;10:59:01;11:01:02;11:02:02;");
    }

    @Test
    void getTimeWhenServerWasDownOneTime(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "500 10:59:01\n"
                    + "400 11:01:02\n"
                    + "300 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat(result.toString()).contains("log10:57:01;11:02:02;");
    }
}