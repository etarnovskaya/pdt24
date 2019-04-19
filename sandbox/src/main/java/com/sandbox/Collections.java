package com.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args){
//    List<String> lang = new ArrayList<>();
//    lang.add("Java");
//    lang.add("c#");
    List<String> lang = Arrays.asList("Java", "Python","C#");
    for (String l : lang) {
      System.out.println(l);
    }
  }
}
