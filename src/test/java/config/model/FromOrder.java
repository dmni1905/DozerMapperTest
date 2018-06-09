package config.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FromOrder {
    private Map<BigInteger, Set<BigInteger>> fromItems = new HashMap();

    public Map<BigInteger, Set<BigInteger>> getFromItems() {
        return fromItems;
    }

    public void setFromItems(Map<BigInteger, Set<BigInteger>> fromItems) {
        this.fromItems = fromItems;
    }

}
