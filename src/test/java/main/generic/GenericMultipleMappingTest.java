package main.generic;

import config.model.from.FromOrderItem;
import config.model.from.characteristic.FromAbstractCharacteristicValue;
import config.model.from.characteristic.FromBigIntegerCharacteristicValue;
import config.model.from.characteristic.FromMultipleCharacteristicValue;
import config.model.to.ToOrderItem;
import config.model.to.characteristic.ToAbstractCharacteristicValue;
import main.ADozerTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GenericMultipleMappingTest extends ADozerTest {
    private FromOrderItem fromOrderItem;
    private FromMultipleCharacteristicValue fromMultipleValue;
    private List<FromAbstractCharacteristicValue<?>> fromCharacteristicValuesUnderMultiple = new ArrayList<>();
    private BigInteger zeroId = BigInteger.ZERO;

    @Before
    public void initFromOrder() {
        fromOrderItem = new FromOrderItem();
        fromMultipleValue = new FromMultipleCharacteristicValue();

        FromBigIntegerCharacteristicValue fromBigValue1 = new FromBigIntegerCharacteristicValue();
        fromBigValue1.setId(BigInteger.ONE);
        fromBigValue1.setValue(new BigInteger("1990"));

        FromBigIntegerCharacteristicValue fromBigValue2 = new FromBigIntegerCharacteristicValue();
        fromBigValue2.setId(BigInteger.TEN);
        fromBigValue2.setValue(new BigInteger("2000"));

        fromCharacteristicValuesUnderMultiple.add(fromBigValue1);
        fromCharacteristicValuesUnderMultiple.add(fromBigValue2);

        fromMultipleValue.setValue(fromCharacteristicValuesUnderMultiple);

        Map<BigInteger, FromAbstractCharacteristicValue<?>> fromCharObjects = new HashMap<>();
        fromCharObjects.put(zeroId, fromMultipleValue);

        fromOrderItem.setCharacteristicValues(fromCharObjects);
    }

    @Test // - test FAILED
    public void multipleGenericFieldTest() throws IOException {
        ToOrderItem toOrderItem = mapper.map(fromOrderItem, ToOrderItem.class);

        assertNotNull(toOrderItem);
        assertTrue(checkFromClass(fromOrderItem, toOrderItem));

        ToAbstractCharacteristicValue<?> toMultipleCharacteristicValue = toOrderItem.getCharacteristicValues().get(zeroId);
        assertNotNull(toMultipleCharacteristicValue);
        assertTrue(checkFromClass(
                fromMultipleValue,
                toMultipleCharacteristicValue)
        );

        Object toAbstractCharacteristicValue = toMultipleCharacteristicValue.getValue();
        assertTrue(toAbstractCharacteristicValue instanceof List);

        List toAbstractCharacteristicValuesUnderMultiple = (List) toAbstractCharacteristicValue;
        for (int i = 0; i < toAbstractCharacteristicValuesUnderMultiple.size(); i++){
            assertTrue(checkFromClass( // -> fail
                    fromCharacteristicValuesUnderMultiple.get(i),
                    toAbstractCharacteristicValuesUnderMultiple.get(i)
            ));
        }
    }
}
