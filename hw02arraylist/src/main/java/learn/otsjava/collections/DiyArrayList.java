package learn.otsjava.collections;

import java.util.*;

public class DiyArrayList<E> implements List<E> {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    private E[] elements;
    private int size;

    public DiyArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public DiyArrayList(int initialCapacity) {
        if (initialCapacity < 0 || MAX_ARRAY_SIZE < initialCapacity) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        elements = (E[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new DiyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        if (ensureCapacity()) {
            elements[size++] = e;
            return true;
        } else {
            return false;
        }
    }

    private boolean ensureCapacity() {
        if (elements.length == size) {
            int newLength = elements.length * 2;
            if (newLength > MAX_ARRAY_SIZE) {
                return false;
            }
            elements = Arrays.copyOf(elements, newLength);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DiyListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        return new DiyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (!isEmpty()) {
            sb.append(elements[0]);
            for (int i = 1; i < size; i++) {
                sb.append(", ").append(elements[i]);
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void checkIndex(int index) {
        Objects.checkIndex(index, size);
    }

    private class DiyIterator implements Iterator<E> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[cursor++];
        }
    }

    private class DiyListIterator implements ListIterator<E> {
        private static final int IDX_INVALIDATED = -1;

        private int cursor;
        private int indexOfReturned = IDX_INVALIDATED;

        public DiyListIterator(int indexOfFirstElement) {
            this.cursor = indexOfFirstElement;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            indexOfReturned = cursor++;
            return elements[indexOfReturned];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            indexOfReturned = --cursor;
            return elements[indexOfReturned];
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            // indexOfReturned = IDX_INVALIDATED;
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (indexOfReturned == IDX_INVALIDATED) {
                throw new IllegalStateException();
            }
            elements[indexOfReturned] = e;
        }

        @Override
        public void add(E e) {
            // indexOfReturned = IDX_INVALIDATED;
            throw new UnsupportedOperationException();
        }
    }
}
