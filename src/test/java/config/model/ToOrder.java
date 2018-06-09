package config.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ToOrder {
    private Map<BigInteger, Set<BigInteger>> toItems = new HashMap();

    public Map<BigInteger, Set<BigInteger>> getToItems() {
        return toItems;
    }

    public void setToItems(Map<BigInteger, Set<BigInteger>> toItems) {
        this.toItems = toItems;
    }
}
