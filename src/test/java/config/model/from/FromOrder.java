package config.model.from;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FromOrder {
    private FromOrderItem orderItem;

    private Map<BigInteger, Set<BigInteger>> fromItems = new HashMap();

    public FromOrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(FromOrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Map<BigInteger, Set<BigInteger>> getFromItems() {
        return fromItems;
    }

    public void setFromItems(Map<BigInteger, Set<BigInteger>> fromItems) {
        this.fromItems = fromItems;
    }

}
