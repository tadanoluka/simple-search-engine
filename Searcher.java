package search;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Searcher {

    private SearchingStrategy strategy;

    public Searcher(SearchingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Person> getResults(String searchQuery,
                                   List<Person> inputData,
                                   Map<String, Set<Integer>> invertedIndexMap) {
        return strategy.search(searchQuery, inputData, invertedIndexMap);
    }
}
