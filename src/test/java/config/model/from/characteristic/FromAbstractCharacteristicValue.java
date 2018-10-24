package config.model.from.characteristic;

import java.math.BigInteger;
import java.util.Objects;

public abstract class FromAbstractCharacteristicValue<T> {
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
