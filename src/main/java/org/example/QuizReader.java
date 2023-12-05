package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class QuizReader {

    public static void main(String[] args) {
        String folderPath = "src/main/java/org/example/Questions/"; // Replace with your folder path
        readQuestionsFromFolder(folderPath);
    }

    private static void readQuestionsFromFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();


        if (listOfFiles != null) {

            List<File> filesList = Arrays.asList(listOfFiles);

            Collections.shuffle(filesList);

            for (File file : filesList) {
                if (file.isFile()) {
                    readQuestionFromFile(file);
                }
            }
        }
    }

    private static void readQuestionFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            String question = "";
            String answer = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Question:")) {
                    question = line.substring(9).trim(); // Extract question
                } else if (line.startsWith("Answer:")) {
                    answer = line.substring(7).trim(); // Extract answer
                }
            }

            Scanner inputScanner = new Scanner(System.in);


            System.out.println("Question: " + question);

            if ((answer.toLowerCase()).equals(inputScanner.nextLine().toLowerCase())){
                System.out.println("Correct");
            }else{
                System.out.println("Wrong! Answer: " + answer);

            }

            System.out.println();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        }
    }
}
