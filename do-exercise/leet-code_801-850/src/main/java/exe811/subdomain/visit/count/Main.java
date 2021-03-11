package exe811.subdomain.visit.count;


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /*["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 *Output:
 * ["901 mail.com", "50 yahoo.com", "900 google.mail.com", "5 wiki.org", "5 org", "1 intel.mail.com",
 *"951 com"]
        */
        String[] cpdomains = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(solution.subdomainVisits(cpdomains));
    }
}
