package search;

import java.util.List;
import java.util.Map;
import java.util.Set;

interface SearchingStrategy {
    abstract List<Person> search(String searchQuery,
                         List<Person> inputData,
                         Map<String, Set<Integer>> invertedIndexMap);
}
