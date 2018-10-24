package config.model.to;

import config.model.to.characteristic.ToAbstractCharacteristicValue;

import java.math.BigInteger;
import java.util.Map;

public class ToOrderItem {
    private String name;
    private Map<BigInteger, ToAbstractCharacteristicValue<?>> characteristicValues;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<BigInteger, ToAbstractCharacteristicValue<?>> getCharacteristicValues() {
        return characteristicValues;
    }

    public void setCharacteristicValues(Map<BigInteger, ToAbstractCharacteristicValue<?>> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }
}
