package JavaPrograms;

public class LearnSearchingSorting {
    // Linear Search Algorithm in Java
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search Algorithm in Java (Array must be sorted)
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (arr[middle] == target) {
                return middle;
            } else if (target < arr[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    // Bubble Sort Algorithm in Java
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) break;
        }
    }

    // Selection Sort Algorithm in Java
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    // Insertion Sort Algorithm in Java
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int prev = i - 1;

            while (prev >= 0 && arr[prev] > curr) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = curr;
        }
    }

    // Merge Sort Algorithm in Java
    public static void mergeSort(int[] arr, int left, int right) {
        if (arr == null || arr.length <= 1) return;
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 23, 45, 70, 11, 15};
        int target = 11;
        System.out.println("Index of Target " + target + ": " + linearSearch(arr, target)); // Output: 4

        int[] sortedArr = {10, 11, 15, 23, 45, 70};
        int target2 = 15;
        System.out.println("Index of Target " + target2 + ": " + binarySearch(sortedArr, target2)); // Output: 2

        int[] arr2 = {5, 10, 2, 8, 1, 4};
        bubbleSort(arr2);
        printArray(arr2); // Output: 1 2 4 5 8 10

        int[] arr3 = {64, 25, 12, 22, 11};
        selectionSort(arr3);
        printArray(arr3); // Output: 11 12 22 25 64

        int[] arr4 = {12, 11, 13, 5, 6};
        insertionSort(arr4);
        printArray(arr4); // Output: 5 6 11 12 13

        int[] arr5 = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(arr5, 0, arr5.length - 1);
        printArray(arr5); // Output: 3 9 10 27 38 43 82
    }

}