package TURF.DIA_2;
import java.util.*;

public class Solution {
    public List<List<String>> findSequences(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if (beginWord.equals(endWord)) {
            result.add(Collections.singletonList(beginWord));
            return result;
        }

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }

        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(Collections.singletonList(beginWord));

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> currentLevelVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> currentPath = queue.poll();
                String lastWord = currentPath.get(currentPath.size() - 1);

                for (int j = 0; j < lastWord.length(); j++) {
                    char[] wordArray = lastWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[j] = c;
                        String newWord = new String(wordArray);

                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            List<String> newPath = new ArrayList<>(currentPath);
                            newPath.add(newWord);

                            if (newWord.equals(endWord)) {
                                result.add(newPath);
                                found = true;
                            } else {
                                queue.offer(newPath);
                                currentLevelVisited.add(newWord);
                            }
                        }
                    }
                }
            }
            visited.addAll(currentLevelVisited);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String beginWord1 = "der";
        String endWord1 = "dfs";
        List<String> wordList1 = Arrays.asList("des", "der", "dfr", "dgt", "dfs");
        System.out.println(solution.findSequences(beginWord1, endWord1, wordList1));

        String beginWord2 = "gedk";
        String endWord2 = "geek";
        List<String> wordList2 = Arrays.asList("geek", "gefk");
        System.out.println(solution.findSequences(beginWord2, endWord2, wordList2));
    }
}
