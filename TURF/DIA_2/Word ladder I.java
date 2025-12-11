package TURF.DIA_2;

import java.util.*;

 class Solution {
    public int wordLadderLength(String startWord, String targetWord, List<String> wordList) {
        if (!wordList.contains(targetWord)) {
            return 0;
        }
        
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(startWord);
        
        Set<String> visited = new HashSet<>();
        visited.add(startWord);
        
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] wordArray = word.toCharArray();
                
                for (int j = 0; j < wordArray.length; j++) {
                    char originalChar = wordArray[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[j] = c;
                        String newWord = new String(wordArray);
                        
                        if (newWord.equals(targetWord)) {
                            return level + 1;
                        }
                        
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                    wordArray[j] = originalChar; // Restore the original character
                }
            }
            level++;
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<String> wordList1 = Arrays.asList("des", "der", "dfr", "dgt", "dfs");
        System.out.println(solution.wordLadderLength("der", "dfs", wordList1));  // Output: 3

        List<String> wordList2 = Arrays.asList("geek", "gefk");
        System.out.println(solution.wordLadderLength("gedk", "geek", wordList2));  // Output: 2

        List<String> wordList3 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println(solution.wordLadderLength("hit", "cog", wordList3));  // Output: 5
    }
}
