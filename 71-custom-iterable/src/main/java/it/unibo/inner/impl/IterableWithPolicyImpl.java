package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

/**
 * Generic class which implements the {@link IterableWithPolicy} interface.
 * <br>
 * Represents an Iterable collection with a policy to filter the elements during the iteration.
 * @param <T> Class of the elements stored in the collection
 */
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] array;
    private Predicate<T> policy;

    /**
     * All the elements of the collection will be considered for the iteration.
     * @param array Elements to be added to the collection
     */
    public IterableWithPolicyImpl(final T[] array) {
        this(
            array,
            new Predicate<T>() {
                @Override
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    /**
     * Initialize the class so that the iteration skips the elements using the filter selected.
     * @param array Elements to be added to the collection
     * @param pred Rule to filter the elements during the iteration
     */
    public IterableWithPolicyImpl(final T[] array, final Predicate<T> pred) {
        this.array = Arrays.copyOf(array, array.length);
        this.policy = pred;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.policy = filter;
    }

    private class PolicyIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            int i = index;

            while (i < array.length && !policy.test(array[i])) {
                i++;
            }

            return i < array.length;
        }

        @Override
        public T next() {
            T elem;
            elem = array[index++];

            while (!policy.test(elem)) {
                elem = array[index++];
            }

            return elem;
        }

    }
}
