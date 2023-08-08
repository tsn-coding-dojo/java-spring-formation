package com.thales.formation.mutation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MutantClassTest {
    @Test
    public void whenPalindrom_thenAccept() {
        MutantClass palindromeTester = new MutantClass();
        assertTrue(palindromeTester.isPalindrome("noon"));
    }

    @Disabled // remove this annotation to increase mutation coverage
    @Test
    public void whenNotPalindrom_thanReject() {
        MutantClass palindromeTester = new MutantClass();
        assertFalse(palindromeTester.isPalindrome("box"));
    }

    @Disabled // remove this annotation to increase mutation coverage
    @Test
    public void whenNearPalindrom_thanReject() {
        MutantClass palindromeTester = new MutantClass();
        assertFalse(palindromeTester.isPalindrome("neon"));
    }
}