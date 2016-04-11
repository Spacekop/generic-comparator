package com.discoshiny;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Spacekop on 4/11/16.
 *
 * test class that sucks
 */
public class GenericComparatorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static List<Pojo> getExampleList() throws Exception {
        String pojoListJson = Resources.toString(Resources.getResource("pojoList.json"), Charset.forName("UTF-8"));

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        return mapper.readValue(pojoListJson, mapper.getTypeFactory().constructCollectionType(List.class, Pojo.class));
    }

    @Test
    public void sort_SingleSortField_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(Pojo::getTheBusiness)
                .build();

        List<Pojo> pojoList = getExampleList();
        Collections.sort(pojoList, genericComparator);

        assertTrue(pojoList.get(0).getTheBusiness() == 1);
    }

    @Test
    public void sort_TwoSortFields_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getWaysInWhichMyLifeIsAFailure().size())
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = getExampleList();
        Collections.sort(pojoList, genericComparator);

        assertTrue(pojoList.get(0).getTheBusiness() == 1);
    }

    @Test
    public void sort_OneSideNull_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = getExampleList();
        pojoList.get(0).getNestedPojo().setTheKindOfBirdWhatNestsHere(null);
        Collections.sort(pojoList, genericComparator);

        assertTrue(pojoList.get(0).getTheBusiness() == 1);
    }

    @Test
    public void sort_BothSidesNull_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = getExampleList();
        pojoList.get(0).getNestedPojo().setTheKindOfBirdWhatNestsHere(null);
        pojoList.get(1).getNestedPojo().setTheKindOfBirdWhatNestsHere(null);
        Collections.sort(pojoList, genericComparator);

        assertTrue(pojoList.get(0).getTheBusiness() == 2);
    }

    @Test
    public void sort_LambdaThrowsNPE_Throw() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getNestedPojo().getTheKindOfBirdWhatNestsHere())
                .build();

        List<Pojo> pojoList = getExampleList();
        pojoList.get(0).setNestedPojo(null);

        exception.expect(NullPointerException.class);
        Collections.sort(pojoList, genericComparator);
    }
}
