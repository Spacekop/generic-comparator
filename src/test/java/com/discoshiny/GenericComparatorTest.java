package com.discoshiny;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * Created by Spacekop on 4/11/16.
 *
 * test class that sucks
 */
public class GenericComparatorTest {

    private static List<Pojo> getExampleList() throws Exception {
        String pojoListJson = Resources.toString(Resources.getResource("pojoList.json"), Charset.forName("UTF-8"));

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        return mapper.readValue(pojoListJson, mapper.getTypeFactory().constructCollectionType(List.class, Pojo.class));
    }

    @Test
    public void sort_WithExamplePojo_Success() throws Exception {
        GenericComparator<Pojo> genericComparator = new GenericComparator.Builder<Pojo>()
                .addSortPredicate(pojo -> pojo.getWaysInWhichMyLifeIsAFailure().size())
                .addSortPredicate(Pojo::getTheBusiness)
                .build();

        List<Pojo> pojoList = getExampleList();
        Collections.sort(pojoList, genericComparator);
    }
}
