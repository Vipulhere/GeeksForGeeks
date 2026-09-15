class Solution {
    public int dominantPairs(int[] arr) {
        int n = arr.length;
        int mid = n / 2;

        int[] left = new int[mid];
        int[] right = new int[mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
            right[i] = arr[mid + i];
        }

        java.util.Arrays.sort(left);
        java.util.Arrays.sort(right);

        int count = 0;
        int j = 0;

        for (int i = 0; i < mid; i++) {
            while (j < mid && (long) left[i] >= 5L * right[j]) {
                j++;
            }

            count += j;
        }

        return count;
    }
}