package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class IncludingSearch implements SearchingStrategy {

    @Override
    public List<Person> search(String searchQuery, List<Person> inputData, Map<String, Set<Integer>> invertedIndexMap) {
        String[] searchQueryWordArray;
        if (!searchQuery.isEmpty()) {
            searchQueryWordArray = searchQuery.toLowerCase().split("\\s+");
        } else throw new RuntimeException("Empty Search Query");


        Set<Integer> resultIndexes = null;
        for (String word : searchQueryWordArray) {
            if (resultIndexes != null) {
                resultIndexes.retainAll(invertedIndexMap.get(word));
            } else {
                resultIndexes = invertedIndexMap.get(word);
            }
        }

        List<Person> results = new ArrayList<>();

        if (resultIndexes != null && !resultIndexes.isEmpty()) {
            for (int index : resultIndexes) {
                results.add(inputData.get(index));
            }
        }

        return results;
    }
}


