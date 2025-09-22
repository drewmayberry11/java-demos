/*
 * File: MaxSumSubarrayFixedK.java
 * Purpose: Optimal fixed-size sliding window for max sum of size k.
 * Notes:
 *   - O(n) time, O(1) extra space
 *   - Overflow-safe (uses long for running sum)
 *   - Returns both max sum and [start, end] indices
 */

import java.util.Arrays;

public class MaxSumSubArrayFixedK {

    // Return {maxSum, startIndex, endIndex}
    // Uses long for sum but preserves indices as int.
    public static long[] maxSumFixedK(int[] arr, int k) {
        final int n = arr.length;
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");
        if (k > n)
            throw new IllegalArgumentException("k must be <= array length");

        // 1) Build initial window sum
        long windowSum = 0L;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        long maxSum = windowSum;
        int bestStart = 0; // [bestStart, bestStart + k - 1]

        // 2) Slide the window across the array
        for (int i = k; i < n; i++) {
            // Add new element, remove outgoing element
            windowSum += arr[i] - arr[i - k];

            // Track best window (prefer earliest if equal — deterministic)
            if (windowSum > maxSum) {
                maxSum = windowSum;
                bestStart = i - k + 1;
            }
        }

        return new long[] { maxSum, bestStart, bestStart + k - 1 };
    }

    // Tiny demo
    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 1, 3, 2 };
        int k = 3;
        long[] ans = maxSumFixedK(arr, k);
        System.out.println("Max sum = " + ans[0] +
                ", window = [" + ans[1] + ", " + ans[2] + "], subarray = " +
                Arrays.toString(Arrays.copyOfRange(arr, (int) ans[1], (int) ans[2] + 1)));
        // Max sum = 9, window = [2, 4], subarray = [5, 1, 3]
    }
}
