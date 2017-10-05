package ru.job4j.arrayiterators;

import org.junit.Test;
import ru.job4j.arrayiteratos.IntegerIt;
import ru.job4j.arrayiteratos.IteratorConverter;
import ru.job4j.arrayiteratos.IteratorsStorage;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing IteratorConverter.
 *
 * @author gkuznetsov.
 * @version 0.1.
 * @since 02.10.2017.
 */
public class IteratorConverterTest {
    /**
     * Testing iterator converter.
     */
    @Test
    public void whenItHasThreeInnerIt() {
        Iterator it1 = new IntegerIt(new int[]{4, 2, 0, 4, 6, 4, 9});
        Iterator it2 = new IntegerIt(new int[]{5, 9, 8, 7, 5});
        Iterator it3 = new IntegerIt(new int[]{1, 3, 5, 6, 7, 0, 9, 8, 4});
        Iterator[] its = {it1, it2, it3};
        Iterator<Iterator<Integer>> itStorage = new IteratorsStorage(its);
        Iterator<Integer> resultIterator = new IteratorConverter().convert(itStorage);
        for (int i = 0; i < 7; i++) {
            resultIterator.next();
        }
        int result = resultIterator.next();
        assertThat(result, is(5));
    }
    /**
     * Testing iterator converter.
     */
    @Test
    public void whenItHasTwoInnerIt2() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new IteratorConverter().convert(it);
        convert.next();
        int result = convert.next();
        assertThat(result, is(2));
    }
}
