package com.home.interview.algo.sort.core;

/*
Find the smallest element using a linear scan and move it to the front. Then, find the second smallest and move it,
	again doing a linear scan. Continue doing this until all the elements are in place. O(n^2).

http://en.wikipedia.org/wiki/Selection_sort

64 25 12 22 11

11 25 12 22 64

11 12 25 22 64

11 12 22 25 64

11 12 22 25 64


+ it does not depend on the initial arrangement of data
-the comparisons required is O(n^2)=> appropriated for small N
=> advantageous when data moves are costely but comparisons are not

					worst		best		avarage
-----------------------------------------------------
selection sort	  |	O(n^2)	   O(n^2)		O(n^2)	
-----------------------------------------------------		
merge sort 	  	  |	O(nlogn)   O(nlogn))	O(nlogn)  O(n) extra space
-----------------------------------------------------
quicksort	  	  |	O(n^2)	  O(nlogn)		O(nlogn)	
-----------------------------------------------------		
insertionsort 	  |	O(n^2)	  	O(n)		  O(n^2)

*/


public class SelectionSort {

	public void selectionSort(int []a){
		int position, temp;
		for (int i = 0 ; i < a.length-1 ; i++ ){
			position = i;							//la inceput minimul este primul element din sirul nesortat array [i]
			for(int j = i+1; j < a.length; j++) {
				if(a[position] > a[j]) {			//cauta un element care este mai mic decat minimul selectat ca fiind primul
					position = j;					//salvam pozitia noului minim
				}
			}
			if(position != i) {						//daca s-a schimbat pozitia minimului initial (s-a gasit un minim mai mic decat primul selectat)
				temp = a[i];
				a[i] = a[position];					//il punem pe ultima pozitie in sirul sortat
				a[position] = temp;					// elementul care credeam ca este minim ia locul minimuli gasit (este trecut in sirul nesortat)
			}
		}											//procedeul se repeta pana cand sirul este ordonat
	}
	
	public static void main(String[] args) {
		int x[]={4, 6, 7, 8, 1, 2, 11};
		SelectionSort object=new SelectionSort();
		object.selectionSort(x);
		for(int i = 0; i < x.length; i++)
			System.out.print(x[i] + " ");

	}

}
