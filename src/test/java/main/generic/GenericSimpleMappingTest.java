package main.generic;

import config.model.from.FromOrder;
import config.model.from.FromOrderItem;
import config.model.from.characteristic.FromAbstractCharacteristicValue;
import config.model.from.characteristic.FromBigIntegerCharacteristicValue;
import config.model.from.characteristic.FromStringCharacteristicValue;
import config.model.to.ToOrder;
import config.model.to.characteristic.ToAbstractCharacteristicValue;
import main.ADozerTest;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GenericSimpleMappingTest extends ADozerTest {

    @Test // - test passed
    public void abstractFieldTEST() throws IOException {
        FromOrder fromOrder = new FromOrder();
        FromOrderItem fromOrderItem = new FromOrderItem();

        FromBigIntegerCharacteristicValue fromCharValue = new FromBigIntegerCharacteristicValue();
        fromCharValue.setId(BigInteger.ZERO);
        fromCharValue.setValue(new BigInteger("1994"));

        Map<BigInteger, FromAbstractCharacteristicValue<?>> fromCharObjects = new HashMap<>();
        fromCharObjects.put(BigInteger.TEN, fromCharValue);

        fromOrderItem.setCharacteristicValues(fromCharObjects);
        fromOrder.setOrderItem(fromOrderItem);


        ToOrder toOrder = ADozerTest.mapper.map(fromOrder, ToOrder.class);
        testingResult(fromOrder, toOrder);
    }

    @Test // - test passed
    public void abstractFieldTest() throws IOException{
        FromOrder fromOrder = new FromOrder();
        FromOrderItem fromOrderItem = new FromOrderItem();

        FromBigIntegerCharacteristicValue fromBigValue1 = new FromBigIntegerCharacteristicValue();
        fromBigValue1.setId(BigInteger.ZERO);
        fromBigValue1.setValue(new BigInteger("1990"));

        FromStringCharacteristicValue fromStrValue2 = new FromStringCharacteristicValue();
        fromStrValue2.setId(BigInteger.ONE);
        fromStrValue2.setValue("2000_string");

        Map<BigInteger, FromAbstractCharacteristicValue<?>> fromCharObjects = new HashMap();
        fromCharObjects.put(BigInteger.ZERO, fromBigValue1);
        fromCharObjects.put(BigInteger.ONE, fromStrValue2);

        fromOrderItem.setCharacteristicValues(fromCharObjects);
        fromOrder.setOrderItem(fromOrderItem);

        ToOrder toOrder = ADozerTest.mapper.map(fromOrder, ToOrder.class);
        testingResult(fromOrder, toOrder);
    }

    private void testingResult(FromOrder fromOrder, ToOrder toOrder){
        assertNotNull(toOrder);
        assertTrue(checkFromClass(fromOrder, toOrder));

        assertNotNull(toOrder.getOrderItem());
        assertTrue(checkFromClass(fromOrder.getOrderItem(), toOrder.getOrderItem()));

        assertNotNull(toOrder.getOrderItem().getCharacteristicValues());

        Map<BigInteger, ToAbstractCharacteristicValue<?>> toCharacteristicValues = toOrder.getOrderItem().getCharacteristicValues();
        for (Map.Entry<BigInteger, FromAbstractCharacteristicValue<?>> fromEntity : fromOrder.getOrderItem().getCharacteristicValues().entrySet()){
            BigInteger fromId = fromEntity.getKey();
            FromAbstractCharacteristicValue<?> fromValue = fromEntity.getValue();

            ToAbstractCharacteristicValue<?> toValue = toCharacteristicValues.get(fromId);
            assertNotNull(toValue);
            assertTrue(checkFromClass(fromValue, toValue));
            assertTrue(toValue.getValue().equals(fromValue.getValue()));
        }
    }
}
