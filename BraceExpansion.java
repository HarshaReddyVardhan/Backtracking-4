// Time Complexity: O(k^n), where n = number of groups, k = average characters per group
// Space Complexity: O(n + k^n), for recursion stack and result storage

public class Solution {

    List<List<Character>> groups;
    List<String> ans;

    public String[] bracketExpansion(String s) {
        groups = new ArrayList<>();
        ans = new ArrayList<>();
        int i = 0;

        // Step 1: Parse the string into groups
        while (i < s.length()) {
            List<Character> gr = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        gr.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                gr.add(s.charAt(i)); // Regular character
            }
            i++; // Move past '}' or character
            Collections.sort(gr); // Ensure lexicographical order
            groups.add(gr);
        }

        // Step 2: Backtracking to generate combinations
        helper(0, new StringBuilder());

        // Step 3: Convert list to array and return
        return ans.toArray(new String[0]);
    }

    private void helper(int i, StringBuilder sb) {
        if (i == groups.size()) {
            ans.add(sb.toString());
            return;
        }

        int len = sb.length(); // Backtracking point

        for (char ch : groups.get(i)) {
            sb.append(ch);
            helper(i + 1, sb);
            sb.setLength(len); // Undo append
        }
    }
}
