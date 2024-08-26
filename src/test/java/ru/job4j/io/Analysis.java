package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String readLine;
            boolean badFlag = false;
            boolean goodFlag = false;
            String formatSourceFile = source.split("\\.")[1]
                    + System.lineSeparator()
                    + System.lineSeparator();
            writer.write(formatSourceFile);
            StringJoiner joiner = new StringJoiner(";", "", ";" + System.lineSeparator());
            while ((readLine = reader.readLine()) != null) {
                String[] arr = readLine.split(" ");
                if (("400".equals(arr[0]) || "500".equals(arr[0])) && !badFlag) {
                    joiner.add(arr[1]);
                    badFlag = true;
                }
                if (("200".equals(arr[0]) || "300".equals(arr[0])) && badFlag) {
                    joiner.add(arr[1]);
                    goodFlag = true;
                }
                if (goodFlag && badFlag) {
                    badFlag = false;
                    goodFlag = false;
                    writer.write(joiner.toString());
                    joiner = new StringJoiner(";", "", ";" + System.lineSeparator());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/serverTwo.log", "data/target.csv");
    }
}