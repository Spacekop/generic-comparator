package com.discoshiny;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Class that can be used to compare any two objects of the same type for sorting purposes.
 * Example:
 *  GenericComparator&lt;MyClass&gt; comparator = GenericComparator.Builder&lt;MyClass&gt;()
 *      .addSortPredicate(o -> o.getFirstSortProperty())
 *      .addSortPredicate(o -> o.getProperty().getSecondSortProperty())
 *      .build();
 *
 *  Collections.sort(listOfMyClass, comparator);
 *
 * Of course, the results of these sort predicates must be objects that implement Comparable, otherwise this is
 * impossible.
 *
 * @param <T> Type of objects being compared
 */
public final class GenericComparator<T> implements Comparator<T>, Serializable {
    private final List<Function<T, Comparable>> orderedSortPredicates;

    private GenericComparator(final List<Function<T, Comparable>> orderedSortPredicates) {
        this.orderedSortPredicates = orderedSortPredicates;
    }

    /**
     * Builder class for GenericComparator&lt;T&gt;.
     * @param <T> Type of objects being compared
     */
    public static class Builder<T> {
        private final List<Function<T, Comparable>> sortPredicates = new ArrayList<Function<T, Comparable>>();

        public final Builder<T> addSortPredicate(final Function<T, Comparable> predicate) {
            sortPredicates.add(predicate);
            return this;
        }

        public final GenericComparator<T> build() {
            return new GenericComparator<T>(sortPredicates);
        }
    }


    public int compare(final T o1, final T o2) {
        for (Function<T, Comparable> func : orderedSortPredicates) {
            int result;
            Comparable o1Value = func.apply(o1);
            Comparable o2Value = func.apply(o2);

            if (o1Value == null && o2Value == null) {
                result = 0;
            } else if (o1Value == null) {
                result = 1;
            } else if (o2Value == null) {
                result = -1;
            } else {
                result = o1Value.compareTo(o2Value);
            }

            if (result != 0) {
                return result;
            }
        }

        return 0;
    }
}
