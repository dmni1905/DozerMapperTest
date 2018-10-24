package main;

import config.model.from.FromOrder;
import config.model.to.ToOrder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MapMappingTest extends ADozerTest {
    private FromOrder fromOrder;

    @Before
    public void initFromOrder(){
        fromOrder = new FromOrder();

        Map<BigInteger, Set<BigInteger>> items = new HashMap<>();
        Set<BigInteger> itValues = new HashSet<>();
        itValues.add(new BigInteger("50000"));
        items.put(BigInteger.ONE, itValues);

        fromOrder.setFromItems(items);
    }

    @Test // - test FAILED
    // TODO: https://github.com/DozerMapper/dozer/issues/640
    public void mapOfSetTest() throws IOException{
        ToOrder toOrder = mapper.map(fromOrder, ToOrder.class);

        assertNotNull(toOrder);
        assertTrue(checkFromClass(fromOrder, toOrder));

        assertNotNull(toOrder.getToItems());
        assertTrue(checkFromClass(fromOrder.getOrderItem(), toOrder.getOrderItem()));

        Set<BigInteger> itemIds = toOrder.getToItems().get(BigInteger.ONE);
        assertTrue(!itemIds.isEmpty());
    }
}
