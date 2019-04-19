package com.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void primesTest(){
    Assert.assertTrue(Primes.isPrime(11));
  }
}
