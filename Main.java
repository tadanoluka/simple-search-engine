package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean isRunning = true;
    static Searcher searcher;

    public static void main(String[] args) {
        List<Person> inputData = initializingDataFromFile(args[1]);
        Map<String, Set<Integer>> invertedIndexMap = initializingInvertedIndexes(inputData);

        while (isRunning) {
            switch (menu()) {
                case 0 -> {
                    System.out.println("\nBye!");
                    isRunning = false;
                }
                case 1 -> {
                    chooseSearchStrategy();
                    startSearch(inputData, invertedIndexMap);
                }
                case 2 -> printList(inputData);
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        }
    }


    public static List<Person> initializingDataFromFile(String path) {
        File file = new File(path);
        List<Person> inputList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(file)){
            while (fileScanner.hasNextLine()) {
                inputList.add(new Person(fileScanner.nextLine().trim().split("\\s+")));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + path);
        }
        return inputList;
    }

    public static Map<String, Set<Integer>> initializingInvertedIndexes(List<Person> inputData) {
        Map<String, Set<Integer>> outputMap = new HashMap<>();

        for (Person person : inputData) {
            for (String elem : person.getAllInfoList()) {
                elem = elem.toLowerCase();
                if (outputMap.containsKey(elem)) {
                    outputMap.get(elem).add(person.getIndex());
                } else {
                    Set<Integer> indexesArray = new HashSet<>();
                    indexesArray.add(person.getIndex());
                    outputMap.put(elem, indexesArray);
                }
            }
        }
        return outputMap;
    }

    public static int menu() {
        System.out.println();
        System.out.println("""
            === Menu ===
            1. Find a person
            2. Print all people
            0. Exit
        """.trim());
        while (true) {
            String temp = scanner.nextLine();
            if (!temp.isBlank()) {
                return Integer.parseInt(temp);
            }
        }
    }

    private static void chooseSearchStrategy() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "ALL" -> searcher = new Searcher(new IncludingSearch());
            case "ANY" -> searcher = new Searcher(new ORSearch());
            case "NONE" -> searcher = new Searcher(new ExcludingSearch());
            default -> System.out.println("Not valid matching strategy");
        }
    }

    private static void startSearch(List<Person> inputData, Map<String, Set<Integer>> invertedIndexMap) {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String searchQuery = scanner.nextLine();

        List<Person> searchResults = searcher.getResults(searchQuery, inputData, invertedIndexMap);

        if (searchResults == null) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(searchResults.size() + " persons found:");
            for (Person person : searchResults) {
                System.out.println(person.getAllInfoString());
            }
        }
    }

    public static void printList(List<Person> inputData) {
        System.out.println("=== List of people ===");
        for (Person person : inputData) {
            System.out.println(person.getAllInfoString());
        }
    }
}
