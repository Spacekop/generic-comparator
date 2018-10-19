package com.discoshiny;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Spacekop on 4/11/16.
 *
 * test class that sucks
 */
public class GenericComparatorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void sort_SingleSortField_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(Pojo::getTheBusiness)
                .build();

        List<Pojo> pojoList = Fixture.exampleList();
        pojoList.sort(genericComparator);

        assertEquals(1, (int) pojoList.get(0).getTheBusiness());
    }

    @Test
    public void sort_TwoSortFields_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getWaysInWhichMyLifeIsAFailure().size())
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = Fixture.exampleList();
        pojoList.sort(genericComparator);

        assertEquals(1, (int) pojoList.get(0).getTheBusiness());
    }

    @Test
    public void sort_LeftSideNull_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = Fixture.exampleList();
        pojoList.sort(genericComparator);

        assertEquals(1, (int) pojoList.get(0).getTheBusiness());
    }

    @Test
    public void sort_RightSideNull_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = Fixture.exampleList();
        pojoList.get(1).getNestedPojo().setTheKindOfBirdWhatNestsHere(null);
        pojoList.sort(genericComparator);

        assertEquals(2, (int) pojoList.get(0).getTheBusiness());
    }

    @Test
    public void sort_BothSidesNull_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = Fixture.exampleList();
        pojoList.get(0).getNestedPojo().setTheKindOfBirdWhatNestsHere(null);
        pojoList.get(1).getNestedPojo().setTheKindOfBirdWhatNestsHere(null);
        pojoList.sort(genericComparator);

        assertEquals(2, (int) pojoList.get(0).getTheBusiness());
    }

    @Test
    public void sort_LambdaThrowsNPE_Throw() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = Fixture.exampleList();
        pojoList.get(0).setNestedPojo(null);

        exception.expect(NullPointerException.class);
        pojoList.sort(genericComparator);
    }
}
