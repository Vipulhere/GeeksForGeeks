//{ Driver Code Starts
// Initial Template for Java

import java.math.*;
import java.util.*;

class Multiply {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String a = sc.next();
            String b = sc.next();
            Solution g = new Solution();
            System.out.println(g.multiplyStrings(a, b));

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public String multiplyStrings(String s1, String s2) {
        // Remove leading zeros
        s1 = removeLeadingZeros(s1);
        s2 = removeLeadingZeros(s2);

        // If any number is "0" after cleaning
        if (s1.equals("0") || s2.equals("0")) return "0";

        boolean negative = false;
        if (s1.charAt(0) == '-') {
            negative = !negative;
            s1 = s1.substring(1);
        }
        if (s2.charAt(0) == '-') {
            negative = !negative;
            s2 = s2.substring(1);
        }

        int m = s1.length(), n = s2.length();
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int num1 = s1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int num2 = s2.charAt(j) - '0';
                int sum = num1 * num2 + res[i + j + 1];

                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < res.length && res[idx] == 0) idx++; // skip leading zeros

        for (; idx < res.length; idx++) {
            sb.append(res[idx]);
        }

        if (negative) sb.insert(0, '-');

        return sb.toString();
    }

    private String removeLeadingZeros(String s) {
        int idx = 0;
        boolean isNegative = false;
        if (s.length() > 0 && s.charAt(0) == '-') {
            isNegative = true;
            idx++;
        }
        while (idx < s.length() && s.charAt(idx) == '0') {
            idx++;
        }
        String trimmed = s.substring(idx);
        if (trimmed.length() == 0) return "0";
        return isNegative ? "-" + trimmed : trimmed;
    }
}
