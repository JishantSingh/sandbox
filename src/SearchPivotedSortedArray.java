/**
 * bool pred(idx 1, idx2)
 * <p>
 * pred = (<=)
 * <p>
 * TTTTTTFFFFFFFF
 */

import java.util.Arrays;

public class SearchPivotedSortedArray {
    static int findIndex(int[] arr, int key) {
        int pivot = 0;
        int n = arr.length;
        int sp = 0, ep = n - 1;
        if (arr[0] < arr[n - 1]) {
            int idx = Arrays.binarySearch(arr, key);
            return (idx < 0) ? -1 : idx;
        }
        while (ep - sp > 1) {
            int mid = sp + (ep - sp) / 2;
            if (arr[mid] < arr[sp]) {
                ep = mid;
            } else {
                sp = mid;
            }
        }
        pivot = ep;
        int idx = -1;
        if (key < arr[0]) {
            idx = Arrays.binarySearch(arr, pivot, n, key);
        } else {
            idx = Arrays.binarySearch(arr, 0, pivot, key);
        }
        return (idx < 0) ? -1 : idx;
    }

    public static void main(String[] args) {
        int arr[] = {30, 40, 50, 10, 20};
        System.out.println(findIndex(arr,10));
    }
}
