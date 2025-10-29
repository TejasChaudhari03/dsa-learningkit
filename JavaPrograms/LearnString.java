package JavaPrograms;

import java.util.ArrayList;
import java.util.List;

public class LearnString {
    public static void main(String[] args) {
        String s = "Hello World";
        // Using built-in function
        System.out.println("Statement : " + s);
        System.out.println("Length of last word (built-in): " + lengthOfLastWordUsingBuiltIn(s)); // Output: 5
        // Using two loops
        System.out.println("Length of last word (two loops): " + lengthOfLastWordUsingLoops(s)); // Output: 5
        // Using single loop
        System.out.println("Length of last word (single loop): " + lengthOfLastWordUsingSingleLoop(s)); // Output: 5
        
        // Finding words containing a specific character
        String[] wordsArray = {"apple", "banana", "cherry", "date"};
        char characterToFind = 'a';
        List<Integer> indices = new LearnString().findWordsContainingChar(wordsArray, characterToFind);
        System.out.println("Indices of words containing '" + characterToFind + "': " + indices); // Output: [0, 1, 3]
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
}