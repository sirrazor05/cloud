package com.home.interview.algo.sort.core;

/*
Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
Each iteration, insertion sort removes one element from the input data, finds the location it belongs
within the sorted list, and inserts it there. It repeats until no input elements remain.

Sorting is typically done in-place, by iterating up the array, growing the sorted list behind it.
At each array-position, it checks the value there against the largest value in the sorted list 
(which happens to be next to it, in the previous array-position checked). If larger, it leaves
the element in place and moves to the next. If smaller, it finds the correct position within the
sorted list, shifts all the larger values up to make a space, and inserts into that correct position.

for i <- 1 to length(A)
    x <- A[i]
    j <- i
    while j > 0 and A[j-1] > x
        A[j] <- A[j-1]
        j <- j - 1
    A[j] <- x

O(n2) comparisons, swaps

SELECTION VS INSERTION SORT
Selection sort: scan through the unsorted data looking for the smallest remaining element, then swap it into the position immediately following the sorted data. Repeat until finished. 
Insertion sort: take the element immediately following the sorted data, scan through the sorted data to find the place to put it, and put it there. Repeat until finished.

Relation with other sorting algo:
: very similar with selection sort
+ makes fewer comparisons than selection sort
-requires more writes
 In general, insertion sort will write to the array O(n2) times, whereas selection sort will write only O(n) times.
 For this reason selection sort may be preferable in cases where writing to memory is significantly more expensive than 
 reading, such as with EEPROM or flash memory.
 
 					worst		best		avarage
-----------------------------------------------------
selection sort	  |	O(n^2)	   O(n^2)		O(n^2)	
-----------------------------------------------------		
merge sort 	  	  |	O(nlogn)   O(nlogn))	O(nlogn)  O(n) extra space
-----------------------------------------------------
quicksort	  	  |	O(n^2)	  O(nlogn)		O(nlogn)	
-----------------------------------------------------		
insertionsort 	  |	O(n^2)	  	O(n)		  O(n^2)

example : 7 8 1 4 6
		loop 1
		1
		7 8 1 4 6
		7 8 8 4 6
		7 7 8 4 6 
		1 7 8 4 6
		loop 2
		4
		1 7 8 8 6
		1 7 7 8 6
		1 4 7 8 6
		loop 3
		6
		1 4 7 8 6
		1 4 7 8 8
		1 4 7 7 8
		1 4 6 7 8
*/

public class InsertionSort {
	
	public static void insertionSort(int []a){
		int i, position, temp;
		for(i = 1; i < a.length; i++){				//pentru fiecare element din vector, (primul element se considera sortat)
			temp = a[i];							//salvam elementul curent
			position = i;							//impreuna cu pozitia sa
			while(position > 0 && a[position-1] > temp){		//atata timp cat elementele din lista sortata (stanga elementului curent) sunt mai mari decat elementul curent 	si nu l-am parcurs pe tot
				a[position] = a[position-1];				//deplasama elementul curent din lista sortata la dreapta
				position--;						//trecem la elementul urmator
			}								//odata ce am gasit un element mai mic decat elementul curent temp (sau am parcurs tot sirul)
			a[position] = temp;					//punem elementul curent din sirul neordonat dupa elementul gasit
		}									
	}
	

	public static void main(String[] args) {
		int x[]={4, 6, 7, 8, 1, 2, 11};
		insertionSort(x);
		for(int i = 0; i < x.length; i++) {
			System.out.print(x[i] + " ");
		}
	}

}
