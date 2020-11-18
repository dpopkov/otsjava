package learn.otsjava.collections;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class DiyArrayListTest {

    @Test
    public void whenAddThenGetReturnsAddedElement() {
        List<Integer> list = new DiyArrayList<>();
        assertThat(list.size(), is(0));
        list.add(11);
        assertFalse(list.isEmpty());
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(11));
    }

    @Test
    public void whenAddMoreElementsThenListGrows() {
        List<Integer> list = new DiyArrayList<>(1);
        list.add(11);
        list.add(22);
        list.add(33);
        assertThat(list.get(0), is(11));
        assertThat(list.get(1), is(22));
        assertThat(list.get(2), is(33));
    }

    @Test
    public void testIterator() {
        List<Integer> list = new DiyArrayList<>();
        list.add(11);
        list.add(22);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(11));
        assertThat(it.next(), is(22));
        assertFalse(it.hasNext());
    }

    @Test
    public void testListIterator() {
        List<Integer> list = new DiyArrayList<>();
        list.add(11);
        list.add(22);
        var it = list.listIterator();
        assertTrue(it.hasNext());
        assertFalse(it.hasPrevious());
        assertThat(it.next(), is(11));
        assertTrue(it.hasPrevious());
        assertThat(it.previous(), is(11));
        assertThat(it.next(), is(11));
        assertThat(it.next(), is(22));
        assertFalse(it.hasNext());
        assertThat(it.previous(), is(22));
        assertTrue(it.hasNext());
    }

    @Test
    public void testThatListIteratorCanSetElements() {
        List<Integer> list = new DiyArrayList<>();
        list.add(11);
        list.add(22);
        var it = list.listIterator();
        it.next();
        it.set(101);
        assertThat(list.get(0), is(101));
        it.next();
        it.previous();
        it.set(202);
        assertThat(list.get(1), is(202));
    }

    @Test
    public void canBeUsedInCollectionsAddAllMethod() {
        List<Integer> dest = new DiyArrayList<>();
        List<Integer> source = createdFilledByRangeList(30);
        Collections.addAll(dest, source.toArray(new Integer[0]));
//        assertThat(dest, is(source)); // todo: implement equals
        for (int i = 0; i < source.size(); i++) {
            assertEquals(source.get(i), dest.get(i));
        }
    }

    @Test
    public void canBeUsedInCollectionsCopyMethod() {
        List<Integer> source = createdFilledByRangeList(30);
        List<Integer> dest = new DiyArrayList<>(source.size());
        inflateListWithNulls(dest, source.size());
        Collections.copy(dest, source);
//        assertThat(dest, is(source)); // todo: implement equals
        for (int i = 0; i < source.size(); i++) {
            assertEquals(source.get(i), dest.get(i));
        }
    }

    @SuppressWarnings("Java8ListSort")
    @Test
    public void canBeUsedInCollectionsSortMethod() {
        int range = 30;
        List<Integer> list = new DiyArrayList<>();
        fillListByRangeOfIntegers(list, range);
        Collections.sort(list, Comparator.reverseOrder());
        for (int i = 0; i < range; i++) {
            assertEquals(range - i - 1, list.get(i).intValue());
        }
    }

    private void inflateListWithNulls(List<?> list, int number) {
        for (int i = 0; i < number; i++) {
            list.add(null);
        }
    }

    private List<Integer> createdFilledByRangeList(int size) {
        List<Integer> list = new ArrayList<>();
        fillListByRangeOfIntegers(list, size);
        return list;
    }

    private void fillListByRangeOfIntegers(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
}
