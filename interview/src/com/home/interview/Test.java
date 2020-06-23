package com.home.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
	 public static int getNumber(int number, int n){
         if(number<=n ){
             return number;
         }else{
             return number-n;
         }
     }

    public static int circularArray(int n, List<Integer> endNode) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< endNode.size()-1; i++){
            for(int j = i+1; j<= i+1; j++){
                for(int k = endNode.get(i); k<= endNode.get(j); k++){
                	int number = getNumber(k, n);
                    Integer count = map.get(number);
                    if(count == null){
                        map.put(number, 1);
                    }else{
                        map.put(number,count+1);
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(max<entry.getValue()) {
                max = entry.getValue();
            }
        }
        int smallIndex = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == max && entry.getKey() > smallIndex) {
                smallIndex = entry.getKey();
            }
        }
        return smallIndex;


    }
	public static void main(String[] args) {
		List<Integer> in = new ArrayList<>();
		in.add(1);
		in.add(5);
		in.add(10);
		in.add(5);
		 
		System.out.println(Test.circularArray(10, in));
		
	}
}
