package manfred.end.mutation.test;

import static org.junit.Assert.assertTrue;
import manfred.end.testing.mutation.Palindrome;
import org.junit.Test;

public class PalindromeTest {
    @Test
    public void when_palindrome_then_accept() {
        Palindrome palindromeTester = new Palindrome();
        assertTrue(palindromeTester.isPalindrome("noon"));
    }
}
