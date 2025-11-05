package JavaPrograms;
import java.util.HashMap;
import java.util.Map;
public class LearnArrays {
    
    // 🔹 Utility printer
    private static void printArray(int[] arr){
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }


    // Remove duplicates from a sorted array
    private static int removeDuplicatesSorted(int[] a1) {
        int x=0;
        for(int i=0;i<a1.length;i++){
            if(a1[i]>a1[x]){
                x++;
                a1[x]=a1[i];
            }
        }
        return x+1;
    }

    // Remove all occurrences of a value
    private static int removeAllOccurrences(int[] a2, int val) {
        int x=0;
        for(int i=0;i<a2.length;i++){
            if(a2[i]!=val){
                a2[x]=a2[i];
                x++;
            }
        }
        return x;
    }

    // Reverse a string array
    private static String[] reverseString(String[] str){
        for(int i =0 ; i<str.length/2;i++){
            String temp = str[i];
            str[i]=str[str.length-1-i];
            str[str.length-1-i]=temp;
        }
        return str;
    }

    // Maximum profit from stock prices
    private static int maxProfit(int[] prices){
        int minPrice=prices[0];
        int max = 0;

        for(int i=0;i<prices.length;i++){
            if(prices[i]-minPrice>max){
                max = prices[i]-minPrice;
            }

            if(prices[i]<minPrice){
                minPrice=prices[i];
            }
        }
        return max;
    }

    // Merging two sorted arrays
    // Approach 1: 2 pointer
    private static int[] mergeSortedArrays(int[] a,int alen, int[] b, int blen){
        int[] merged = new int[alen + blen];  // new bigger array
        int p1=0;
        int p2=0;

        for(int i=0; i<alen+blen;i++){
            if(p1<alen && (p2>=blen || a[p1]<b[p2])){
                merged[i]=a[p1];
                p1++;
            } else {
                merged[i]=b[p2];
                p2++;
            }
        }
        return merged;
    }

    // Approach 2: Reverse merge/ In place merging

    private static void mergeSortedArraysInPlace(int[] a,int alen, int[] b, int blen) {
        int p1 = alen - 1;
        int p2 = blen - 1;
        int i = alen + blen - 1; // last index in a
        while (p2 >= 0) {
            if (p1 >= 0 && a[p1] > b[p2]) {
                a[i] = a[p1];
                p1--;
            } else {
                a[i] = b[p2];
                p2--;
            }
            i--;
        }
    }

    // Move zeroes
    private static int[] moveZeroes(int[] a){
        int x = 0;

        for(int i=0;i<a.length;i++){
            if(a[i]!=0){
                a[x]=a[i];
                x++;
            }
        }

        for(int i=x;i<a.length;i++){
            a[i]=0;
        }
        return a;
    }

    // Max consecutive ones update when hitting 0
    private static int maxConsecutiveOnes_v1(int[] a){
        int count=0;
        int max=0;

        for(int i=0;i<a.length;i++){
            if(a[i]==1){
                count++;
            } else {
                max = Math.max(max,count);
                count=0;
            }
        }
        max = Math.max(max,count); // <-- final check needed
        return max;
    }

    // Max consecutive ones update at every step
    private static int maxConsecutiveOnes_v2(int[] a){
        int count=0;
        int max=0;

        for(int i=0;i<a.length;i++){
            if(a[i]==1){
                count++;
                max = Math.max(max,count); // <-- update immediately
            } else {
                count=0;
            }
        }
        return max;
    }

    // Missing Number
    private static int missingNumber(int[] a) {
        int n = a.length;
        int total = (n * (n + 1)) / 2;

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return total - sum;
    }

    // Single Number | XOR approach
    private static int singleNumber(int[] a) {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result = result ^ a[i];
        }
        return result;
    }

    // Single Number | HashMap approach
    private static int singleNumber_HashMap(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : a) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1; // should not reach here
    }
    public static void main(String[] args) {
        // ---------------- Remove Duplicates ----------------
        int[] a1 = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4};
        System.out.print("Input for removeDuplicatesSorted: ");
        printArray(a1);
        int newSize1 = removeDuplicatesSorted(a1);
        System.out.print("Output (newSize=" + newSize1 + "): ");
        for (int i = 0; i < newSize1; i++) System.out.print(a1[i] + " ");
        System.out.println("\n");

        // ---------------- Remove All Occurrences ----------------
        int[] a2 = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4};
        System.out.print("Input for removeAllOccurrences (remove 2): ");
        printArray(a2);
        int newSize2 = removeAllOccurrences(a2, 2);
        System.out.print("Output (newSize=" + newSize2 + "): ");
        for (int i = 0; i < newSize2; i++) System.out.print(a2[i] + " ");
        System.out.println("\n");

        // ---------------- Reverse String ----------------
        String[] str = {"h","e","l","l","o"};
        System.out.print("Input for reverseString: ");
        for (String s : str) System.out.print(s);
        System.out.println();
        String[] reversed = reverseString(str);
        System.out.print("Output: ");
        for (String s : reversed) System.out.print(s);
        System.out.println("\n");
        
        // ---------------- Reverse String of char array ----------------
        char[] s = {'h','e','l','l','o'};
        System.out.print("Input for reverseString (char array): ");
        for (char c : s) System.out.print(c);
        System.out.println();

        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }

        System.out.print("Output: ");
        for (char c : s) System.out.print(c);
        System.out.println("\n");

        // ---------------- Max Profit (Stocks) ----------------
        int[] prices = {7,1,5,3,6,4};
        System.out.print("Input prices: ");
        printArray(prices);
        System.out.println("Max profit: " + maxProfit(prices) + "\n");

        // ---------------- Merge Sorted Arrays (new array) ----------------
        int[] arrA = {1,3,5,7};
        int[] arrB = {2,4,6,8};
        System.out.print("Inputs for mergeSortedArrays: ");
        printArray(arrA);
        printArray(arrB);
        int[] merged = mergeSortedArrays(arrA, arrA.length, arrB, arrB.length);
        System.out.print("Merged array: ");
        printArray(merged);
        System.out.println();

        // ---------------- Merge Sorted Arrays In Place ----------------
        int[] arrInPlace = {1,3,5,7,0,0,0,0}; // space for merge
        int[] arrB2 = {2,4,6,8};
        System.out.print("Input A (with buffer): ");
        printArray(arrInPlace);
        System.out.print("Input B: ");
        printArray(arrB2);
        mergeSortedArraysInPlace(arrInPlace, 4, arrB2, arrB2.length);
        System.out.print("Merged in place: ");
        printArray(arrInPlace);
        System.out.println();

        // ---------------- Move Zeroes ----------------
        int[] arrZero = {0,1,0,3,12};
        System.out.print("Input for moveZeroes: ");
        printArray(arrZero);
        int[] movedZero = moveZeroes(arrZero);
        System.out.print("Output: ");
        printArray(movedZero);
        System.out.println();

        // ---------------- Max Consecutive Ones ----------------
        int[] binaryArr = {1,1,0,1,1,1};
        System.out.print("Input for maxConsecutiveOnes: ");
        printArray(binaryArr);
        System.out.println("v1 Result: " + maxConsecutiveOnes_v1(binaryArr));
        System.out.println("v2 Result: " + maxConsecutiveOnes_v2(binaryArr));
        System.out.println();

        // ---------------- Missing Number ----------------
        int[] arrMissing = {3,0,1};
        System.out.print("Input for missingNumber: ");
        printArray(arrMissing);
        System.out.println("Missing number: " + missingNumber(arrMissing) + "\n");

        // ---------------- Single Number | Using XOR ----------------
        int[] arrSingle = {4,1,2,1,2};
        System.out.print("Input for singleNumber: ");
        printArray(arrSingle);
        System.out.println("Single number (appears once): " + singleNumber(arrSingle));

       // ---------------- Single Number | Using HashMap ----------------
        System.out.print("Input for singleNumber_HashMap: ");
        printArray(arrSingle);
        System.out.println("Single number (appears once) using HashMap: " + singleNumber_HashMap(arrSingle)); 
    }

}