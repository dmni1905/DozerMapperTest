package main.generic;

import config.model.from.FromOrderItem;
import config.model.from.characteristic.FromAbstractCharacteristicValue;
import config.model.from.characteristic.FromBigIntegerCharacteristicValue;
import config.model.from.characteristic.FromStringCharacteristicValue;
import config.model.to.ToOrderItem;
import main.ADozerTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GenericMapInitMappingTest extends ADozerTest {
    private FromBigIntegerCharacteristicValue fromBigValue1;
    private FromStringCharacteristicValue fromStrValue2;

    @Before
    public void initFromOrder() {
        fromBigValue1 = new FromBigIntegerCharacteristicValue();
        fromBigValue1.setId(BigInteger.ZERO);
        fromBigValue1.setValue(new BigInteger("1990"));

        fromStrValue2 = new FromStringCharacteristicValue();
        fromStrValue2.setId(BigInteger.ONE);
        fromStrValue2.setValue("2000_string");
    }

    @Test // - test passed
    public void mapInitTest1() throws IOException {
        Map<BigInteger, FromAbstractCharacteristicValue<?>> fromCharObjects = new HashMap();
        fromCharObjects.put(BigInteger.ZERO, fromBigValue1);
        fromCharObjects.put(BigInteger.ONE, fromStrValue2);

        FromOrderItem fromOrderItem = new FromOrderItem();
        fromOrderItem.setCharacteristicValues(fromCharObjects);

        ToOrderItem toOrderItem = mapper.map(fromOrderItem, ToOrderItem.class);
        testingResult(fromOrderItem, toOrderItem);
    }

    @Test // - test THREW NO_SUCH_METHOD_EXCEPTION
    public void mapInitTest2() throws IOException {
        Map<BigInteger, FromAbstractCharacteristicValue<?>> fromCharObjects = new HashMap() {{
            put(BigInteger.ZERO, fromBigValue1);
            put(BigInteger.ONE, fromStrValue2);
        }};

        FromOrderItem fromOrderItem = new FromOrderItem();
        fromOrderItem.setCharacteristicValues(fromCharObjects);

        ToOrderItem toOrderItem = mapper.map(fromOrderItem, ToOrderItem.class);
        testingResult(fromOrderItem, toOrderItem);
    }

    private void testingResult(FromOrderItem fromOrderItem, ToOrderItem toOrderItem){
        assertNotNull(toOrderItem);
        assertTrue(checkFromClass(fromOrderItem, toOrderItem));

        assertNotNull(fromOrderItem.getCharacteristicValues());
        assertNotNull(toOrderItem.getCharacteristicValues());

        // check Map.get(BigInteger.ZERO)
        assertNotNull(fromOrderItem.getCharacteristicValues().get(BigInteger.ZERO));
        assertNotNull(toOrderItem.getCharacteristicValues().get(BigInteger.ZERO));
        assertTrue(checkFromClass(
                fromOrderItem.getCharacteristicValues().get(BigInteger.ZERO),
                toOrderItem.getCharacteristicValues().get(BigInteger.ZERO))
        );

        // check Map.get(BigInteger.ZERO)
        assertNotNull(fromOrderItem.getCharacteristicValues().get(BigInteger.ONE));
        assertNotNull(toOrderItem.getCharacteristicValues().get(BigInteger.ONE));
        assertTrue(checkFromClass(
                fromOrderItem.getCharacteristicValues().get(BigInteger.ONE),
                toOrderItem.getCharacteristicValues().get(BigInteger.ONE))
        );
    }
}
