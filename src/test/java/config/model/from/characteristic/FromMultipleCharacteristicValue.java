package config.model.from.characteristic;

import java.util.ArrayList;
import java.util.List;

public class FromMultipleCharacteristicValue extends FromAbstractCharacteristicValue<List<? extends FromAbstractCharacteristicValue<?>>> {

    @Override
    public void setValue(List<? extends FromAbstractCharacteristicValue<?>> value) {
        if (value == null) {
            super.setValue(value);
        } else {
            ArrayList<? extends FromAbstractCharacteristicValue<?>> listToSet = new ArrayList<>(value);
            listToSet.trimToSize();
            super.setValue(listToSet);
        }
    }
}
