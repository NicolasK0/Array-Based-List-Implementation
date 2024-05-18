package ProjectAArrayBasedListFiles;

import java.util.Arrays;
import java.util.Objects;

public class ArrayFrontBackCappedList<T> implements FrontBackCappedList<T> {
    private T[] list;
    private int numberOfElements;

    public ArrayFrontBackCappedList(int capacity) {
        if (!isValidCapacity(capacity)) {
            throw new IllegalArgumentException("Capacity must not be a negative number");
        }
        this.list = (T[]) new Object[capacity];

//        Object[] objs = {2, 4, 6, 8, 9, null, null, null, null, null};
//        this.list = (T[]) objs;
//        this.numberOfElements = 5;
    }

    @Override
    public String toString() {
        T[] filledArray = Arrays.copyOf(list, numberOfElements);
        return String.format("size=%s; capacity=%s;\t%s", numberOfElements, list.length, Arrays.toString(filledArray));
    }

    private boolean isValidCapacity(int capacity) {
        return 0 <= capacity;
    }

    @Override
    public boolean addFront(T newEntry) {
        if (isFull()) {
            return false;
        }
        shiftArrayBack();
        this.list[0] = newEntry;
        numberOfElements++;

        return true;
    }

    private void shiftArrayBack() {
        for (int i = numberOfElements; i > 0; i--) {
            this.list[i] = this.list[i - 1];
        }
    }

    private void shiftArrayFront() {
        for (int i = 0; i < numberOfElements - 1; i++) {
            this.list[i] = this.list[i + 1];
        }
    }

    @Override
    public boolean addBack(T newEntry) {
        if (isFull()) {
            return false;
        }
        this.list[numberOfElements] = newEntry;
        numberOfElements++;

        return true;
    }

    @Override
    public T removeFront() {
        if (isEmpty()) {
            return null;
        }

        T removedElement = this.list[0];
        shiftArrayFront();
        numberOfElements--;

        return removedElement;
    }

    @Override
    public T removeBack() {
        if (isEmpty()) {
            return null;
        }

        T removedElement = this.list[numberOfElements - 1];
        numberOfElements--;
        return removedElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size(); i++) {
            this.list[i] = null;
        }
        this.numberOfElements = 0;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (!isValidPosition(givenPosition)) {
            return null;
        }

        return this.list[givenPosition];
    }

    private boolean isValidPosition(int givenPosition) {
        return 0 <= givenPosition && givenPosition < size();
    }

    @Override
    public int indexOf(T anEntry) {
        for (int i = 0; i < numberOfElements; i++) {
            if (Objects.equals(anEntry, this.list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T anEntry) {
        for (int i = numberOfElements - 1; i >= 0; i--) {
            if (Objects.equals(anEntry, this.list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < numberOfElements; i++) {
            if (Objects.equals(anEntry, this.list[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return this.list.length == this.numberOfElements;
    }
}
