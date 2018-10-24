package main;

import config.model.from.FromOrder;
import config.model.from.FromOrderItem;
import config.model.to.ToOrder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SipmleMappingTest extends ADozerTest {
    private FromOrder fromOrder;
    private String orderItemName = "ORDER_ITEM#1";

    @Before
    public void initFromOrder(){
        fromOrder = new FromOrder();

        FromOrderItem оrderItem = new FromOrderItem();
        оrderItem.setName(orderItemName);

        fromOrder.setOrderItem(оrderItem);
    }

    @Test // - test passed
    public void orderItemTest() throws IOException {
        ToOrder toOrder = mapper.map(fromOrder, ToOrder.class);

        assertNotNull(toOrder);
        assertTrue(checkFromClass(fromOrder, toOrder));

        assertNotNull(toOrder.getOrderItem());
        assertTrue(checkFromClass(fromOrder.getOrderItem(), toOrder.getOrderItem()));

        assertNotNull(toOrder.getOrderItem().getName());
        assertTrue(orderItemName.equals(toOrder.getOrderItem().getName()));
    }
}
