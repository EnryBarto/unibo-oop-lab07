package it.unibo.inner.impl;

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

    public IterableWithPolicyImpl(final T[] array) {
        this.array = array;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    private class PolicyIterator implements Iterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return this.index < array.length;
        }

        @Override
        public T next() {
            return array[index++];
        }

    }
}
