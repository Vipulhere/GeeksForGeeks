//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine();
            String[] S = s.split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(S[i]);
            }
            Solution ob = new Solution();
            System.out.println(ob.maxXor(arr));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    //Github -- vipulhere
    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    private void insert(TrieNode root, int num) {
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (root.children[bit] == null) {
                root.children[bit] = new TrieNode();
            }
            root = root.children[bit];
        }
    }

    private int findMaximumXOR(TrieNode root, int num) {
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int toggleBit = 1 - bit;
            if (root.children[toggleBit] != null) {
                maxXor |= (1 << i);
                root = root.children[toggleBit];
            } else {
                root = root.children[bit];
            }
        }
        return maxXor;
    }

    public int maxXor(int[] arr) {
        TrieNode root = new TrieNode();
        int maxResult = 0;
        insert(root, arr[0]);

        for (int i = 1; i < arr.length; i++) {
            maxResult = Math.max(maxResult, findMaximumXOR(root, arr[i]));
            insert(root, arr[i]);
        }
        return maxResult;
    }
}
