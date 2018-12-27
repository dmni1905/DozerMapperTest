package main;

import config.model.from.BooleanTestFrom;
import config.model.to.BooleanTestTo;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BooleanMeppingTest extends ADozerTest {

    @Test // - test FAILED
    public void booleanTest() throws IOException {
        BooleanTestFrom bFrom = new BooleanTestFrom();
        bFrom.setB(true);

        BooleanTestTo bTo = mapper.map(bFrom, BooleanTestTo.class);
        assertNotNull(bTo);
        assertNotNull(bTo.isB());
        assertTrue(bTo.isB());
    }

    @Test
    public void booleanPrimitiveTest() throws IOException {
        BooleanTestFrom bFrom = new BooleanTestFrom();
        bFrom.setB2(true);

        BooleanTestTo bTo = mapper.map(bFrom, BooleanTestTo.class);
        assertNotNull(bTo);
        assertTrue(bTo.isB2());
    }
}
