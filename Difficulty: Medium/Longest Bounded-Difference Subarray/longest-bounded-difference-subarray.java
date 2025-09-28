class Solution {
    public ArrayList<Integer> longestSubarray(int[] arr, int x) {
        // code here
        int n = arr.length;
        int left = 0, bestStart = 0, bestLen = 0;
        Deque<Integer> minDeque = new ArrayDeque<>();
        Deque<Integer> maxDeque = new ArrayDeque<>();

        for (int right = 0; right < n; right++) {
            while (!minDeque.isEmpty() && arr[minDeque.peekLast()] > arr[right]) minDeque.pollLast();
            minDeque.addLast(right);

            while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] < arr[right]) maxDeque.pollLast();
            maxDeque.addLast(right);

            while (arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()] > x) {
                left++;
                if (minDeque.peekFirst() < left) minDeque.pollFirst();
                if (maxDeque.peekFirst() < left) maxDeque.pollFirst();
            }

            if (right - left + 1 > bestLen) {
                bestLen = right - left + 1;
                bestStart = left;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = bestStart; i < bestStart + bestLen; i++) result.add(arr[i]);
        return result;
    }
}