package config.model.from;

import config.model.from.characteristic.FromAbstractCharacteristicValue;

import java.math.BigInteger;
import java.util.Map;

public class FromOrderItem {
    private String name;
    private Map<BigInteger, FromAbstractCharacteristicValue<?>> characteristicValues;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<BigInteger, FromAbstractCharacteristicValue<?>> getCharacteristicValues() {
        return characteristicValues;
    }

    public void setCharacteristicValues(Map<BigInteger, FromAbstractCharacteristicValue<?>> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }
}
