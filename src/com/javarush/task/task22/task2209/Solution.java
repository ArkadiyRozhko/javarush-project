package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));){
            while (fileReader.ready()){
                StringTokenizer stringTokenizer = new StringTokenizer(fileReader.readLine()," ");
                while(stringTokenizer.hasMoreTokens()){
                    words.add(stringTokenizer.nextToken());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //...
        StringBuilder result = getLine(words.toArray(new String[words.size()]));
        System.out.println(result.toString());

    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        Set<String> UniqCities = new HashSet<>(Arrays.asList(words));
        List<String> AllCities = new ArrayList<>();
        AllCities.addAll(UniqCities);

        List<String> finalSolutionPath = null;
        for(String startCity : AllCities){
            Set<String> visitedCities = new HashSet<>();
            List<String> currentPath = new ArrayList<>();
            visitedCities.add(startCity);
            currentPath.add(startCity);
            if(findPath(startCity,visitedCities,currentPath,AllCities)){
                finalSolutionPath = new ArrayList<>(currentPath);
                break;
            }
        }
        if (finalSolutionPath != null) {
            //ArrayList<String> allWords = new ArrayList<>(Arrays.asList(words));
            for(int i=0; i<finalSolutionPath.size(); i++){
                result.append(finalSolutionPath.get(i));
                //allWords.remove(finalSolutionPath.get(i));

                if (i<finalSolutionPath.size()-1) {
                    result.append(" ");
                }

            }
//            for(String word : allWords){
//                result.append(" ");
//                result.append(word);
//            }
        }

        return result;
    }
    private static boolean findPath(String currentCity,Set<String> visitedCity,List<String> currentPath,List<String> AllCities){
        if(visitedCity.size()==AllCities.size()){
            return true;
        }
        char lastChar = Character.toLowerCase(currentCity.charAt(currentCity.length()-1));

        for(String nextCity:AllCities){
            if(!visitedCity.contains(nextCity)){
                char firstCharNext = Character.toLowerCase(nextCity.charAt(0));
                if(firstCharNext==lastChar){
                    visitedCity.add(nextCity);
                    currentPath.add(nextCity);
                    if(findPath(nextCity,visitedCity,currentPath,AllCities)){
                        return true;
                    }
                    currentPath.remove(currentPath.size()-1);
                    visitedCity.remove(nextCity);
                }
            }
        }
        return false;
    }
}
