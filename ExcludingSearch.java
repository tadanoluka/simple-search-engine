package search;

import java.util.*;

class ExcludingSearch implements SearchingStrategy {
    @Override
    public List<Person> search(String searchQuery, List<Person> inputData, Map<String, Set<Integer>> invertedIndexMap) {
        String[] searchQueryWordArray;
        if (!searchQuery.isEmpty()) {
            searchQueryWordArray = searchQuery.toLowerCase().split("\\s+");
        } else throw new RuntimeException("Empty Search Query");


        Set<Integer> resultIndexes = new HashSet<>();
        for (int i = 0; i < inputData.size(); i++) {
            resultIndexes.add(i);
        }
        for (String word : searchQueryWordArray) {
            resultIndexes.removeAll(invertedIndexMap.get(word));
        }

        List<Person> results = new ArrayList<>();

        if (!resultIndexes.isEmpty()) {
            for (int index : resultIndexes) {
                results.add(inputData.get(index));
            }
        }

        return results;
    }
}
