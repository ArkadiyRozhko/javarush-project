package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        //BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        try (BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
                BufferedReader fileReader=new BufferedReader(new FileReader(reader.readLine()))) {
            Set<String> unverified = new HashSet<>();
            while (fileReader.ready()) {
                String[] data = fileReader.readLine().split(" ");
                for (String word : data) {
                    StringBuilder stringBuilder = new StringBuilder(word);
                    stringBuilder.reverse();
                    String reversedWord = stringBuilder.toString();
                    if (unverified.contains(reversedWord)) {
                        unverified.remove(reversedWord);
                        Pair pair = new Pair();
                        pair.first = word;
                        pair.second = reversedWord;
                        result.add(pair);
                    } else {
                        unverified.add(word);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Pair pair:result){
            System.out.println(pair.toString());
        }

    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
