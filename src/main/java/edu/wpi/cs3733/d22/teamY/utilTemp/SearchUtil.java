package edu.wpi.cs3733.d22.teamY.utilTemp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SearchUtil {
  public static int compute_Levenshtein_distanceDP(String str1, String str2) {

    // A 2-D matrix to store previously calculated
    // answers of subproblems in order
    // to obtain the final

    int[][] dp = new int[str1.length() + 1][str2.length() + 1];

    for (int i = 0; i <= str1.length(); i++) {
      for (int j = 0; j <= str2.length(); j++) {

        // If str1 is empty, all characters of
        // str2 are inserted into str1, which is of
        // the only possible method of conversion
        // with minimum operations.
        if (i == 0) {
          dp[i][j] = j;
        }

        // If str2 is empty, all characters of str1
        // are removed, which is the only possible
        // method of conversion with minimum
        // operations.
        else if (j == 0) {
          dp[i][j] = i;
        } else {
          // find the minimum among three
          // operations below

          dp[i][j] =
              minm_edits(
                  dp[i - 1][j - 1]
                      + NumOfReplacement(str1.charAt(i - 1), str2.charAt(j - 1)), // replace
                  dp[i - 1][j] + 1, // delete
                  dp[i][j - 1] + 1); // insert
        }
      }
    }

    return dp[str1.length()][str2.length()];
  }

  // check for distinct characters
  // in str1 and str2

  static int NumOfReplacement(char c1, char c2) {
    return c1 == c2 ? 0 : 1;
  }

  // receives the count of different
  // operations performed and returns the
  // minimum value among them.

  static int minm_edits(int... nums) {

    return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
  }

  /**
   * Sorts the given array of strings using the levenshtein distance to your search term.
   *
   * @param searchTerm the search term
   * @param list the list of strings to sort
   * @return the sorted list by levenshtein distance
   */
  public static ArrayList<String> search(String searchTerm, String[] list) {
    ArrayList<String> sortedList = new ArrayList<>();
    for (String s : list) {
      sortedList.add(s);
    }
    // sort the list by levenshtein distance
    sortedList.sort(Comparator.comparingInt(s -> compute_Levenshtein_distanceDP(searchTerm, s)));
    return sortedList;
  }

  public static String getClosestMatch(String searchTerm, String[] list) {
    return search(searchTerm, list).get(0);
  }

  /**
   * wak algo that compares lev dist per word of s1 w length taken into account
   *
   * @param s1 A possible match
   * @param query The search term
   * @return A score for the match
   */
  public static int getMatchScore(String s1, String query) {
    query = query.toLowerCase();
    if (s1.length() == 0 || query.length() == 0) {
      return 0;
    }
    String[] parsedName = s1.toLowerCase().split(" ");
    int d1 = 100;
    for (String s : parsedName) {
      if (s.length() < query.length()) {
        continue;
      }
      int dist = SearchUtil.compute_Levenshtein_distanceDP(s.substring(0, query.length()), query);
      if (dist < d1) {
        d1 = dist;
      }
    }
    return d1;
  }
}
