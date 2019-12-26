#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#define MAX 60000l
#define MLD 1000000000.0

void insertionSort(int arr[], int low, int high){
    int i, j, key;
    for (i = low; i < high; i++) { 
        key = arr[i];
        j = i - 1; 
        while (j >= 0 && arr[j] > key) { 
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
int partition (int arr[], int low, int high) 
{ 
    int pivot = arr[high];    // pivot 
    int i = (low - 1);
    int swap;
    for (int j = low; j <= high- 1; j++) 
    { 
        if (arr[j] < pivot) 
        {
            i++;
            // zamieniamy arr[i] z arr[j]
            swap = arr[i];
            arr[i] = arr[j];
            arr[j] = swap;
        } 
    }
    // zamieniamy arr[i+1] z arr[high]
    swap = arr[i+1];
    arr[i+1] = arr[high];
    arr[high] = swap;
    return (i + 1); 
} 

void quickSort(int arr[], int low, int high) 
{ 
    if (low < high) 
    { 
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1); 
        quickSort(arr, pi + 1, high); 
    } 
}

void quickSort1(int arr[], int low, int high) 
{ 
    if (low < high) 
    {
        if(high-low+1<4){
            insertionSort(arr, low, high);
        }
        else{
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1); 
        quickSort(arr, pi + 1, high);
        }
    } 
}

void printArray(int arr[], int size) 
{ 
    int i; 
    for (i=0; i < size; i++) 
        printf("%d ", arr[i]);
    printf("\n");
} 

int main(){
    // int arr[] = {3, 7, 1, 9, 8, 5,10,14,14,2};
    // int arr1[] = {3, 7, 1, 9, 8, 5,10,14,14,2};
    int arr[] = {15,14,13,12,11,10,5,4,3,2,1};
    int arr1[] = {15,14,13,12,11,10,5,4,3,2,1};

    int n = sizeof(arr)/sizeof(arr[0]); 
    // Sortujemy
    struct timespec tp0, tp1;
    double Tn,Fn,x;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
    quickSort(arr, 0, n-1);
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
    Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
    printf("czas podstawowego: %3.10lf\n",Tn);
    // modyfikowana
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
    quickSort1(arr1, 0, n-1);
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);
    Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
    printf("czas zmodyfikowanego: %3.10lf\n",Tn);


    printf("Posortowana Tablica: ");
    // wyświetlamy
    printArray(arr, n);
    printArray(arr1, n); 
    return 0; 
}