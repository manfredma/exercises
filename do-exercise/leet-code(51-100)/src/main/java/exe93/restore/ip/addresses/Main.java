/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

 */
package exe93.restore.ip.addresses;

/**
 * @author manfred on 2019/8/25.
 */
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.restoreIpAddresses("0000"));
        System.out.println(s.restoreIpAddresses("1111"));
        System.out.println(s.restoreIpAddresses("255255255255"));
        System.out.println(s.restoreIpAddresses("25525511135"));
    }
}
