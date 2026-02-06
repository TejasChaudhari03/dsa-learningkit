package javaprograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        String[] wordsArray = { "apple", "banana", "cherry", "date" };
        char characterToFind = 'a';
        List<Integer> indices = new LearnString().findWordsContainingChar(wordsArray, characterToFind);
        System.out.println("Statement : " + String.join(", ", wordsArray));
        System.out.println("Indices of words containing '" + characterToFind + "': " + indices); // Output: [0, 1, 3]

        // Jewels and Stones
        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println("Statement : " + stones + " and jewels : " + jewels);        
        System.out.println("Number of jewels in stones (brute force): " + numJewelsInStonesBruteForce(jewels, stones)); // Output: 3
        System.out.println("Number of jewels in stones (using Set): " + numJewelsInStones(jewels, stones)); // Output: 3

        // Most frequent vowels and consonants
        String inputString = "hello world";
        System.out.println("Statement : " + inputString);   
        System.out.println("Most frequent vowels or consonants count: " + mostFrequentVowelsAndConsonants(inputString)); // Output: 3

        // Balanced String Split
        String balancedString = "RLRRLLRLRL";
        System.out.println("Statement : " + balancedString);
        System.out.println("Number of balanced strings: " + balancedStringSplit(balancedString)); // Output: 4

        // Reverse String II
        String strToReverse = "abcdefg";
        int k = 2;
        System.out.println("Statement : " + strToReverse);
        System.out.println("k is the number of characters to be reversed : " + k);
        System.out.println("Reversed string: " + reverseStr(strToReverse, k)); // Output: "bacdfeg"

        // Palindrome Check using extra space
        String palindromeTest = "A man, a plan, a canal: Panama";
        System.out.println("Statement : " + palindromeTest);
        System.out.println("Is palindrome: " + isPalindrome(palindromeTest)); // Output: true

        // Palindrome Check without extra space
        System.out.println("Is palindrome without extra space: " + isPalindromeWithoutExtraSpace(palindromeTest)); // Output: true

        // Largest Odd Number in a String
        String numString = "35427";
        System.out.println("Statement : " + numString);
        System.out.println("Largest odd number: " + largestOddNumber(numString)); // Output

        // Longest Common Prefix
        String[] strs = { "flower", "flow", "flight" };
        System.out.println("Statement : " + String.join(", ", strs));
        System.out.println("Longest common prefix: " + longestCommonPrefix(strs)); // Output

        // Valid Anagram using HashMap
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Statement : " + s1 + ", " + t1);
        System.out.println("Is anagram (using map): " + isAnagramUsingMap(s1, t1)); // Output: true

        // Valid Anagram using Sorting
        System.out.println("Is anagram (using sorting): " + isAnagramUsingSorting(s1, t1)); // Output: true

        // Isomorphic Strings
        String str1 = "egg";
        String str2 = "add";
        System.out.println("Statement : " + str1 + ", " + str2);
        System.out.println("Are isomorphic: " + isIsomorphic(str1, str2)); // Output: true


        // Group Anagrams using HashMap
        String[] anagramStrs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println("Statement : " + String.join(", ", anagramStrs));
        List<List<String>> groupedAnagrams = groupAnagramsUsingHashMap(anagramStrs);
        System.out.println("Grouped anagrams: " + groupedAnagrams); // Output: [["eat","tea","ate"],["tan","nat"],["bat"]]


        // Group Anagrams using Sorted Array Key
        List<List<String>> groupedAnagramsSortedKey = groupAnagramsUsingSortedArrayKey(anagramStrs);
        System.out.println("Grouped anagrams (using sorted array key): " + groupedAnagramsSortedKey); // Output: [["eat","tea","ate"],["tan","nat"],["bat"]]

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
    // 1. String with only spaces: " " -> 0
    // Explanation: The trim() method removes leading and trailing spaces, and
    // split(" ") divides the string into words based on spaces. The last word's
    // length is then returned.

    // Using two loops
    public static int lengthOfLastWordUsingLoops(String s) {
        int length = 0;
        int i = s.length() - 1;

        // Skip trailing spaces
        while (i >= 0) {
            if (s.charAt(i) != ' ')
                break;
            i--;
        }

        // Count the length of the last word
        while (i >= 0) {
            if (s.charAt(i) == ' ')
                break;
            length++;
            i--;
        }

        return length;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. String with only spaces: " " -> 0
    // Explanation: The first loop trims spaces from the end of the string, and the
    // second loop counts the length of the last word until a space is encountered.

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
    // 1. String with only spaces: " " -> 0
    // Explanation: This method uses a single loop to traverse the string from the
    // end, counting the length of the last word while skipping trailing spaces.



    // Find words containing character and return new array with the index of those
    // words
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
    // Time Complexity: O(m * n) where m is the number of words and n is the average
    // length of each word
    // Space Complexity: O(k) where k is the number of words containing the
    // character x
    // Edge Cases:
    // 1. Empty array: [] -> []
    // Explanation: The method iterates through each word and checks if it contains
    // the specified character. If it does, the index of that word is added to the
    // result list.

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

        /*
         * Alternative approach without for-each loop using charAt()
         * for(int i =0; i<stones.length();i++){
         * for (int j = 0; j < jewels.length(); j++) {
         * if (stones.charAt(i) == jewels.charAt(j)) {
         * count++;
         * break;
         * }
         * }
         * }
         */
        return count;
    }
    // Time Complexity: O(m * n) where m is the length of stones and n is the length
    // of jewels
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. Empty jewels: "" -> 0
    // 2. Empty stones: "a" -> 0
    // Explanation: The method will return 0 if there are no jewels or stones to
    // compare.

    // Jewels and Stones - Using Set
    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
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
    // Time Complexity: O(m + n) where m is the length of stones and n is the length
    // of jewels
    // Space Complexity: O(n) where n is the length of jewels
    // Edge Cases:
    // 1. Empty jewels: "" -> 0
    // 2. Empty stones: "a" -> 0
    // Explanation: The method uses a Set to store jewels for O(1) average time
    // complexity lookups, making it more efficient than the brute force approach.

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
    // Space Complexity: O(k) where k is the number of unique characters in the
    // string
    // Edge Cases:
    // 1. Empty string: "" -> 0
    // Explanation: The method counts the frequency of each character in the string,
    // then determines the maximum frequency among vowels and consonants separately,
    // returning the higher of the two.

    // Balanced String Split
    public static int balancedStringSplit(String s) {
        int r = 0;
        int l = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R')
                ++r;
            else
                ++l;
            if (r == l) {
                count++;
                r = 0;
                l = 0;
            }
        }
        return count;
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(1)
    // Explanation: The method counts the number of balanced substrings where the
    // number of 'R's equals the number of 'L's by maintaining two counters and
    // incrementing the count whenever they are equal.

    // Reverse String II
    public static String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i += 2 * k) {
            int left = i;
            int right = Math.min(i + k - 1, arr.length - 1);
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return new String(arr);
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(n) for the character array
    // Edge Cases:
    // 1. k greater than string length: "abc", k=5 -> "cba" -- entire string
    // reversed
    // 2. k equals string length: "abcd", k=4 -> "dcba" -- entire string reversed
    // 3. k equals 1: "abcd", k=1 -> "abcd" -- no change
    // 4. Empty string: "", k=2 -> "" -- no change
    // 5. String length not a multiple of 2k: "abcdefg", k=2 -> "bacdfeg"
    // Explanation: The method reverses the first k characters for every 2k
    // characters in the string. If there are fewer than k characters left, it
    // reverses all of them. If there are between k and 2k characters left, it
    // reverses the first k and leaves the rest unchanged.

    // Palindrome Check using extra space
    public static boolean isPalindrome(String s) {
        StringBuilder filteredStr = new StringBuilder();
        StringBuilder reverseStr = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                char lower = Character.toLowerCase(c);
                filteredStr.append(lower);
                reverseStr.insert(0, lower);
            }
        }

        return filteredStr.toString().equals(reverseStr.toString());
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(n) for the filtered and reversed strings
    // Edge Cases:
    // 1. Empty string: "" -> true
    // Explanation: The method filters out non-alphanumeric characters and converts
    // the string to lowercase, then checks if the filtered string is equal to its
    // reverse.

    // Palindrome Check without extra space
    public static boolean isPalindromeWithoutExtraSpace(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. Empty string: "" -> true
    // Explanation: The method uses two pointers to compare characters from the
    // start and end of the string, skipping non-alphanumeric characters and
    // converting to lowercase for comparison.

    // Largest Odd Number in a String
    public static String largestOddNumber(String num) {
        int i = num.length() - 1;

        while (i >= 0) {
            if ((num.charAt(i) - '0') % 2 == 1)
                return num.substring(0, i + 1);
            i--;
        }
        return "";
    }
    // Time Complexity: O(n) where n is the length of the string
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. No odd digits: "2468" -> ""
    // Explanation: The method iterates from the end of the string to find the last
    // odd digit. If found, it returns the substring up to that digit; otherwise, it
    // returns an empty string.

    // Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        int x = 0;
        while (x < strs[0].length()) {
            char ch = strs[0].charAt(x); // reference starting string to match with other
            for (int i = 1; i < strs.length; i++) {
                if (ch != strs[i].charAt(x) || x == strs[i].length()) {
                    return strs[0].substring(0, x);
                }
            }
            ++x;
        }
        return strs[0];
    }
    // Time Complexity: O(s) where s is the sum of all characters in all strings or
    // O(n * m) where n is number of strings and m is length of shortest string
    // Example strs = ["flower","flow","flight"] -> m = 4 (flow is smallest string
    // and prefix cannot be longer than this) and n = 3
    // Space Complexity: O(1)
    // Edge Cases:
    // 1. Empty array: [] -> ""
    // 2. Single string: ["abc"] -> "abc"
    // 3. No common prefix: ["abc", "def"] -> ""

    // Valid Anagram using HashMap
    public static boolean isAnagramUsingMap(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char d : t.toCharArray()) {
            if (!map.containsKey(d) || map.get(d) == 0) {
                return false;
            }
            map.put(d, map.get(d) - 1);
        }

        return true;
    }
    // Time Complexity: O(n) where n is the length of the strings
    // Space Complexity: O(k) where k is the number of unique characters in the
    // strings
    // Edge Cases:
    // 1. Empty strings: "", "" -> true
    // 2. Different lengths: "abc", "ab" -> false
    // 3. Special characters: "a!b@c", "c@b!a" -> true
    // Explanation: The method counts the frequency of each character in the first
    // string and then checks if the second string has the same frequency for all
    // characters.

    // Valid Anagram using Sorting
    public static boolean isAnagramUsingSorting(String s, String t) {
        if (s.length() != t.length())
            return false;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        java.util.Arrays.sort(sArr);
        java.util.Arrays.sort(tArr);

        return java.util.Arrays.equals(sArr, tArr);
    }
    // Time Complexity: O(n log n) where n is the length of the strings
    // Space Complexity: O(1) if sorting in place, otherwise O(n)
    // Edge Cases:
    // 1. Empty strings: "", "" -> true
    // 2. Different lengths: "abc", "ab" -> false
    // 3. Special characters: "a!b@c", "c@b!a" -> true
    // Explanation: The method sorts both strings and then compares them. If they
    // are equal, the strings are anagrams; otherwise, they are not.

    // Valid Anagram using Counting characters
    public static boolean isAnagramUsingCounting(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] count = new int[26]; // Only 26 lowercase letters

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++; // increment for s
            count[t.charAt(i) - 'a']--; // decrement for t
        }

        // Check if all counts are zero
        for (int n : count) {
            if (n != 0)
                return false;
        }

        return true;
    }
    // Time Complexity: O(n) where n is the length of the strings
    // Space Complexity: O(1) since the size of the count array is constant (26 letters)
    // Edge Cases:
    // 1. Empty strings: "", "" -> true
    // 2. Different lengths: "abc", "ab" -> false
    // 3. Special characters: "a!b@c", "c@b!a" -> true



    // Isomorphic Strings
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (!mapST.containsKey(charS) && !mapTS.containsKey(charT)) {
                mapST.put(charS, charT);
                mapTS.put(charT, charS);
            } else {
                if (!Character.valueOf(charT).equals(mapST.get(charS)) || !Character.valueOf(charS).equals(mapTS.get(charT)))
                    return false;
            }
        }

        return true;
    }
    // Time Complexity: O(n) where n is the length of the strings
    // Space Complexity: O(k) where k is the number of unique characters in the strings
    // Edge Cases:
    // 1. Empty strings: "", "" -> true
    // 2. Different lengths: "egg", "addd" -> false
    // Explanation: The method uses two maps to track the character mappings from
    // s to t and from t to s. If a mapping conflict is found, it returns false; otherwise, it returns true.


    // Group Anagrams using HashMap
    public static List<List<String>> groupAnagramsUsingHashMap(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }
    // Time Complexity: O(n * m log m) where n is the number of strings and m is the maximum length of a string
    // Space Complexity: O(n * m) for storing the grouped anagrams
    // Edge Cases:
    // 1. Empty array: [] -> []
    // Explanation: The method sorts each string to create a key and groups anagrams together in a map. Finally, it returns the grouped anagrams as a list of lists

    // Group Anagrams using Sorted Array Key
    public static List<List<String>> groupAnagramsUsingSortedArrayKey(String[] strs) {
          Map<String, List<String>> map = new HashMap<>();
  
          for (String s : strs) {
              int[] freq = new int[26];
              for (char c : s.toCharArray()) {
                  freq[c - 'a']++;
              }
  
              StringBuilder key = new StringBuilder();
              for (int count : freq) {
                  key.append("#").append(count);
              }
  
              map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(s);
          }
  
          return new ArrayList<>(map.values());
    }
    // Time Complexity: O(n * m) where n is the number of strings and m is the maximum length of a string
    // Space Complexity: O(n * m) for storing the grouped anagrams
    // Edge Cases:
    // 1. Empty array: [] -> []
    // 2. Array with one string: ["a"] -> [["a"]]
    // 3. All strings are anagrams: ["eat", "tea", "ate"] -> [["eat", "tea", "ate"]]
    // Explanation: The function uses a frequency array to count the occurrences of each character in the strings, creating a unique key for each group of anagrams. This approach avoids the need to sort each string, improving efficiency.

}