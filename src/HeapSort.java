/*
José Miguel Avellana López
2º GM1
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.lang.*;

public class HeapSort {
    private static class Heap<E> {

        private final ArrayList<E> elements;

        private int heapSize = 0;
        private final Comparator<? super E> comparator;


        private Heap(ArrayList<E> elements, Comparator<E> comparator) {
            this.elements = elements;
            this.comparator = comparator;
        }


        private static int parent(int index) {
            if (index % 2 == 0) {
                return (index - 2) / 2;             //si es par resta dos al indice y divide para dos.
            } else {
                return (index - 1) / 2;             //si es impar resta uno al indice y divide para dos.
            }
        }

        private static int left(int index) {
            return 2 * index + 1;
        }

        private static int right(int index) {
            return 2 * index + 2;
        }

        private boolean hasLeft(int index) {
            //return 2 * index + 1 < elements.size();
            return left(index)<=heapSize;
        }

        private boolean hasRight(int index) {
            //return 2 * index + 2 < elements.size();
            return right(index)<=heapSize;
        }

        private boolean hasParent(int index) {
            return index != 0;
        }



        private void swap(int i){
                E aux = elements.get(i);
                elements.set(i, elements.get(parent(i)));
                elements.set(parent(i), aux);
                if (hasParent(parent(i)) && comparator.compare(elements.get(parent(i)),elements.get(parent(parent(i)))) > 0) {
                        swap(parent(i));
                }
        }

        public void makeThree(){
            do {
                if (hasParent(heapSize) && comparator.compare(elements.get(heapSize), elements.get(parent(heapSize))) > 0) {
                    swap(heapSize);
                }
                heapSize++;
            } while (heapSize < elements.size());
            heapSize--;
        }

        public void deleteArrel(int i){
            E aux = elements.get(i);
            if (hasLeft(i) && (!hasRight(i) && comparator.compare(elements.get(left(i)), (elements.get(i))) > 0)
                || (comparator.compare(elements.get(left(i)), (elements.get(i))) > 0
                && comparator.compare(elements.get(left(i)), (elements.get(right(i)))) > 0)) {        //el izquierdo es mayor.


                    elements.set(i, elements.get(left(i)));
                    elements.set(left(i), aux);
                    deleteArrel(left(i));

            }else if (hasLeft(i) && comparator.compare(elements.get(left(i)), (elements.get(i))) > 0
                    && comparator.compare(elements.get(left(i)), (elements.get(right(i)))) > 0) {       //el derecho es el mayor.


                elements.set(i, elements.get(right(i)));
                elements.set(right(i), aux);
                deleteArrel(right(i));
            }
        }

        public E deleteThree(){
            E aux=elements.get(0);

            elements.set(0, elements.get(heapSize));
            elements.remove(heapSize);
            heapSize--;

            deleteArrel(0);

            return aux;
        }

    }


    public static <E> void sort(ArrayList<E> list, Comparator<? super E> cmp) {
        Heap h = new Heap(list, cmp);
        h.makeThree();
        for(int i=list.size()-1; i>=0; i--){
            list.set(i , (E) h.deleteThree());
        }
    }

    public static <E extends Comparable<? super E>> void sort (ArrayList<E> list){
        Comparator<E> cmp =  new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        };

        sort(list, cmp);
    }

}