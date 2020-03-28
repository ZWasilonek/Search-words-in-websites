package pl.javalearn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Search {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        findPopularWords("http://www.onet.pl");
        System.out.println("Enter key words and \"quit\" when you are done");

        List<String> filteredWords = new ArrayList<>();
        String word = "";

        do {
            word = sc.nextLine();
            if (!word.equals("quit")) {
                String[] words = word.trim().split("[\\s,]");
                for (String w : words) {
                    filteredWords.add(w.trim());
                }
            }
        } while (!word.equals("quit"));

        filterWords(filteredWords);
    }

    static void findPopularWords(String url) {
        Connection connect = Jsoup.connect(url);
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            Path path = Paths.get("popular_words.txt");
            ArrayList<String> outList = new ArrayList<>();

            for (Element elem : links) {
                StringTokenizer tokenizer = new StringTokenizer(elem.text());
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken().replaceAll("\"", "")
                            .replaceAll("\\.", "")
                            .replaceAll(",", "")
                            .replaceAll(":", "")
                            .replaceAll("\\?", "")
                            .replaceAll("!", "")
                            .replaceAll("-", "")
                            .trim()
                            .toLowerCase();
                    if (word.length() > 3) {
                        outList.add(word);
                    }
                }
            }
            Files.write(path, outList);
        } catch (IOException e) {
            System.err.println("Error writing to file popular_words.txt");
        }
    }

    static void filterWords(List<String> filteredWords) {
        Path inputPath = Paths.get("popular_words.txt");
        Path outputPath = Paths.get("filtered_popular_words.txt");
        ArrayList<String> outList = new ArrayList<>();

        try {
            for (String word : Files.readAllLines(inputPath)) {
                boolean filtered = false;
                for (String filteredWord : filteredWords) {
                    if (filteredWord.equals(word)) {
                        filtered = true;
                        break;
                    }
                }
                if (filtered) {
                    outList.add(word);
                }
            }
            Files.write(outputPath, outList);
        } catch (IOException e) {
            System.err.println("File handling error");
        }
    }

}
