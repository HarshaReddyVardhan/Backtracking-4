// Time Complexity: O(k^n) — where n is number of groups and k is average group size.
// Space Complexity: O(n + k^n) — recursion stack + result size.
class Solution {
public:
    vector<string> result;
    vector<vector<char>> groups;

    vector<string> bracketExpansion(string &s) {
        int n = s.length();
        int i = 0;
        vector<char> gp;

        while (i < n) {
            gp.clear();
            if (s[i] == '{') {
                i++;
                while (s[i] != '}') {
                    if (s[i] != ',')
                        gp.push_back(s[i]);
                    i++;
                }
            } else {
                gp.push_back(s[i]);
            }
            i++; // Move past '}' or character

            // Sort the group to maintain lexicographical order
            sort(gp.begin(), gp.end());
            groups.push_back(gp);
        }

        string path = "";
        helper(0, path);
        return result;
    }

    void helper(int i, string &path) {
        if (i == groups.size()) {
            result.push_back(path);
            return;
        }

        for (char ch : groups[i]) {
            path.push_back(ch);
            helper(i + 1, path);
            path.pop_back();
        }
    }
};
