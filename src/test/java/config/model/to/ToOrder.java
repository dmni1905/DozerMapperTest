package config.model.to;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ToOrder {
    private ToOrderItem orderItem;

    private Map<BigInteger, Set<BigInteger>> toItems = new HashMap();

    public ToOrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(ToOrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Map<BigInteger, Set<BigInteger>> getToItems() {
        return toItems;
    }

    public void setToItems(Map<BigInteger, Set<BigInteger>> toItems) {
        this.toItems = toItems;
    }
}
