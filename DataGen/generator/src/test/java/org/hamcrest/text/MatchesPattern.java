/*
2014 String-native pattern matcher from hamcrest:
https://github.com/hamcrest/JavaHamcrest/blob/6b5489e18dc7865b68b164de60b1505f981349b0/hamcrest-library/src/main/java/org/hamcrest/text/MatchesPattern.java
 */

package org.hamcrest.text;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MatchesPattern extends TypeSafeMatcher<String> {
    private final Pattern pattern;

    public MatchesPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    protected boolean matchesSafely(String item) {
        return pattern.matcher(item).matches();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string matching the pattern '" + pattern + "'");
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * exactly matches the given {@link Pattern}.
     */
    @Factory
    public static Matcher<String> matchesPattern(Pattern pattern) {
        return new MatchesPattern(pattern);
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * exactly matches the given regular expression, treated as a {@link Pattern}.
     */
    @Factory
    public static Matcher<String> matchesPattern(String regex) {
        return new MatchesPattern(Pattern.compile(regex));
    }
}