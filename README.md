# Power of Two Max Heap

I built a max heap where every parent has 2^x children. The x value you pass when creating the heap.

## Why I made this

Regular binary heap has 2 children per parent. This one lets you choose - 2,4,8,16 children whatever you want.

## How to use

```java
// x=2 means 4 children per parent
PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2);

heap.insert(50);
heap.insert(30);
heap.insert(40);

int biggest = heap.popMax(); // returns 50
