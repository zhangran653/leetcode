package heap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zhangran
 * @since 2020-03-25
 **/
public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> array;

    public MaxHeap() {
        array = new ArrayList<>();
    }

    public MaxHeap(E[] e) {
        array = new ArrayList<>();
        Collections.addAll(array, e);

        heapify();
    }

    private void heapify() {
        int k = parent(array.size() - 1);
        while (k >= 0) {
            siftDown(k);
            k--;
        }
    }

    public int size() {
        return array.size();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index can not be 0");
        }
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }


    public void add(E e) {
        array.add(e);
        siftUp(size() - 1);

    }

    /**
     * 上浮
     *
     * @param k
     */
    private void siftUp(int k) {
        while (k > 0 && array.get(k).compareTo(array.get(parent(k))) > 0) {
            E temp = array.get(parent(k));
            array.set(parent(k), array.get(k));
            array.set(k, temp);
            k = parent(k);
        }
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("heap is empty");
        }
        return array.get(0);
    }

    public E extractMax() {
        E max = findMax();
        E last = array.get(size() - 1);
        array.set(0, last);
        array.remove(size() - 1);
        siftDown(0);
        return max;
    }

    private void siftDown(int index) {
        while (leftChild(index) < size()) {
            int j = leftChild(index);
            if (rightChild(index) < size() - 1 && array.get(j + 1).compareTo(array.get(j)) > 0) {
                j += 1;
            }
            if (array.get(index).compareTo(array.get(j)) > 0) {
                break;
            }
            E temp = array.get(j);
            array.set(j, array.get(index));
            array.set(index, temp);

            index = j;
        }
    }

    public E replace(E e) {
        E max = findMax();
        array.set(0, e);
        siftDown(0);
        return max;
    }


}
