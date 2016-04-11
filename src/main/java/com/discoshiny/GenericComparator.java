package com.discoshiny;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Class that can be used to compare any two classes for sorting purposes.
 * Example:
 *  GenericComparator&lt;MyClass&gt; comparator = GenericComparator.Builder&lt;MyClass&gt;()
 *      .addSortPredicate(o -> o.getFirstSortProperty())
 *      .addSortPredicate(o -> o.getProperty().getSecondSortProperty())
 *      .build();
 *
 *  Collections.sort(listOfMyClass, comparator);
 */
public class GenericComparator<T> implements Comparator<T> {
    private final List<Function<T, Comparable>> orderedSortPredicates;

    private GenericComparator(List<Function<T, Comparable>> orderedSortPredicates) {
        this.orderedSortPredicates = orderedSortPredicates;
    }

    public static class Builder<T> {
        private final List<Function<T, Comparable>> sortPredicates = new ArrayList<Function<T, Comparable>>();

        public Builder<T> addSortPredicate(Function<T, Comparable> predicate) {
            sortPredicates.add(predicate);
            return this;
        }

        public GenericComparator<T> build() {
            return new GenericComparator<T>(sortPredicates);
        }
    }

    public int compare(T o1, T o2) {
        for (Function<T, Comparable> func : orderedSortPredicates) {
            int result = func.apply(o1).compareTo(func.apply(o2));
            if (result != 0) {
                return result;
            }
        }

        return 0;
    }
}
