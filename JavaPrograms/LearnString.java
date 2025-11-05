package JavaPrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class LearnString {
    public static void main(String[] args) {
        String s = "Hello World";

        // Length of last word in a string
        // 1. Using built-in function
        System.out.println("Statement : " + s);
        System.out.println("Length of last word (built-in): " + lengthOfLastWordUsingBuiltIn(s)); // Output: 5
        // 2. Using two loops
        System.out.println("Length of last word (two loops): " + lengthOfLastWordUsingLoops(s)); // Output: 5
        // 3. Using single loop
        System.out.println("Length of last word (single loop): " + lengthOfLastWordUsingSingleLoop(s)); // Output: 5
        
        // Finding words containing a specific character
        String[] wordsArray = {"apple", "banana", "cherry", "date"};
        char characterToFind = 'a';
        List<Integer> indices = new LearnString().findWordsContainingChar(wordsArray, characterToFind);
        System.out.println("Indices of words containing '" + characterToFind + "': " + indices); // Output: [0, 1, 3]

        // Jewels and Stones
        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println("Number of jewels in stones (brute force): " + numJewelsInStonesBruteForce(jewels, stones)); // Output: 3
        System.out.println("Number of jewels in stones (using Set): " + numJewelsInStones(jewels, stones)); // Output: 3

        // Most frequent vowels and consonants
        String inputString = "hello world";
        System.out.println("Most frequent vowels or consonants count: " + mostFrequentVowelsAndConsonants(inputString)); // Output: 3

        // Balanced String Split
        String balancedString = "RLRRLLRLRL";
        System.out.println("Number of balanced strings: " + balancedStringSplit(balancedString)); // Output: 4
    }

    // Using built-in functions
    public static int lengthOfLastWordUsingBuiltIn(String s) {
        String[] words = s.trim().split(" "); // words array after trimming spaces
        // e.g. ["Hello", "World"]
        return words[words.length - 1].length();
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. String with only spaces: "    " -> 0
    // Explanation: The trim() method removes leading and trailing spaces, and split(" ") divides the string into words based on spaces. The last word's length is then returned.

    // Using two loops
    public static int lengthOfLastWordUsingLoops(String s) {
        int length = 0;
        int i = s.length() - 1;

        // Skip trailing spaces
        while (i >= 0 ) {
           if (s.charAt(i) != ' ')
               break;
            i--;
        }

        // Count the length of the last word
        while (i >= 0 ) {
            if(s.charAt(i) == ' ')
                break;
            length++;
            i--;
        }

        return length;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. String with only spaces: "    " -> 0
    // Explanation: The first loop trims spaces from the end of the string, and the second loop counts the length of the last word until a space is encountered.

    // Using single loop
    public static int lengthOfLastWordUsingSingleLoop(String s) {
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else if (length > 0) {
                break;
            }
        }
        return length;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. String with only spaces: "    " -> 0
    // Explanation: This method uses a single loop to traverse the string from the end, counting the length of the last word while skipping trailing spaces.


    // Find words containing character and return new array with the index of those words
    public List<Integer> findWordsContainingChar(String[] words, char x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                if (c == x) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }
    // Time Complexity: O(m * n) where m is the number of words and n is the average length of each word
    // Space Complexity: O(k) where k is the number of words containing the character x
    // Edge Cases:
    // 1. Empty array: [] -> []
    // Explanation: The method iterates through each word and checks if it contains the specified character. If it does, the index of that word is added to the result list.

    // Jewels and Stones - Brute Force
    public static int numJewelsInStonesBruteForce(String jewels, String stones) {
        int count = 0;
        for (char stone : stones.toCharArray()) {
            for (char jewel : jewels.toCharArray()) {
                if (stone == jewel) {
                    count++;
                    break;
                }
            }
        }

        /*  Alternative approach without for-each loop using charAt()
        * for(int i =0; i<stones.length();i++){
        *    for (int j = 0; j < jewels.length(); j++) {
        *        if (stones.charAt(i) == jewels.charAt(j)) {
        *            count++;
        *            break;
        *        }
        *    }
        *}
        */
        return count;
    }
    // Time Complexity: O(m * n) where m is the length of stones and n is the length of jewels
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. Empty jewels: "" -> 0
    // 2. Empty stones: "a" -> 0
    // Explanation: The method will return 0 if there are no jewels or stones to compare.

    // Jewels and Stones - Using Set
    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<Character>();
        for (char jewel : jewels.toCharArray()) {
            jewelSet.add(jewel);
        }

        int count = 0;
        for (char stone : stones.toCharArray()) {
            if (jewelSet.contains(stone)) {
                count++;
            }
        }
        return count;
    }
    // Time Complexity: O(m + n) where m is the length of stones and n is the length of jewels
    // Space Complexity: O(n) where n is the length of jewels
    // Edge Cases:
    // 1. Empty jewels: "" -> 0
    // 2. Empty stones: "a" -> 0
    // Explanation: The method uses a Set to store jewels for O(1) average time complexity lookups, making it more efficient than the brute force approach.

    // Most frequent vowels and consonants in a string
    public static int mostFrequentVowelsAndConsonants(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        for (var entry : freq.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            if (vowels.contains(key)) {
                maxVowelFreq = Math.max(maxVowelFreq, value);
            } else {
                maxConsonantFreq = Math.max(maxConsonantFreq, value);
            }
        }
        return Math.max(maxVowelFreq, maxConsonantFreq);
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(k) where k is the number of unique characters in the string
    // Edge Cases:
    // 1. Empty string: "" -> 0
    // Explanation: The method counts the frequency of each character in the string, then determines the maximum frequency among vowels and consonants separately, returning the higher of the two.

    // Balanced String Split
    public static int balancedStringSplit(String s) {
        int r=0;
        int l=0;
        int count=0;
        for(int i =0; i<s.length(); i++){
            if(s.charAt(i)=='R')
                ++r;
            else
                ++l;
            if(r==l){
                count++;
                r=0;
                l=0;
            }
        }
        return count;
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(1)
    // Explanation: The method counts the number of balanced substrings where the number of 'R's equals the number of 'L's by maintaining two counters and incrementing the count whenever they are equal.
}