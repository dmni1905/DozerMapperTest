package config.model.to.characteristic;

import java.math.BigInteger;

public abstract class ToAbstractCharacteristicValue<T> {
    private BigInteger id;
    private T value;

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

}
