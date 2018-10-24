package main;

import config.model.from.FromOrder;
import config.model.from.FromOrderItem;
import config.model.from.characteristic.FromAbstractCharacteristicValue;
import config.model.from.characteristic.FromBigIntegerCharacteristicValue;
import config.model.from.characteristic.FromMultipleCharacteristicValue;
import config.model.from.characteristic.FromStringCharacteristicValue;
import config.model.to.ToOrder;
import config.model.to.ToOrderItem;
import config.model.to.characteristic.ToAbstractCharacteristicValue;
import config.model.to.characteristic.ToBigIntegerCharacteristicValue;
import config.model.to.characteristic.ToMultipleCharacteristicValue;
import config.model.to.characteristic.ToStringCharacteristicValue;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import java.util.HashMap;
import java.util.Map;

import static org.dozer.loader.api.FieldsMappingOptions.hintA;
import static org.dozer.loader.api.FieldsMappingOptions.hintB;

public abstract class ADozerTest {
    protected static Mapper mapper;
    private static Map<Class, Class> oeMapping = new HashMap<>();
    static {
        oeMapping.put(FromOrder.class, ToOrder.class);
        oeMapping.put(FromOrderItem.class, ToOrderItem.class);
        oeMapping.put(FromAbstractCharacteristicValue.class, ToAbstractCharacteristicValue.class);
        oeMapping.put(FromBigIntegerCharacteristicValue.class, ToBigIntegerCharacteristicValue.class);
        oeMapping.put(FromStringCharacteristicValue.class, ToStringCharacteristicValue.class);
        oeMapping.put(FromMultipleCharacteristicValue.class, ToMultipleCharacteristicValue.class);

        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(FromOrder.class, ToOrder.class,
                        TypeMappingOptions.oneWay())
                        .fields("fromItems", "toItems")
                        .fields("orderItem", "orderItem");

                mapping(FromOrderItem.class, ToOrderItem.class,
                        TypeMappingOptions.oneWay())
                        .fields("characteristicValues", "characteristicValues",
                                hintA(config.model.from.characteristic.FromBigIntegerCharacteristicValue.class,
                                        config.model.from.characteristic.FromStringCharacteristicValue.class,
                                        config.model.from.characteristic.FromMultipleCharacteristicValue.class),
                                hintB(config.model.to.characteristic.ToBigIntegerCharacteristicValue.class,
                                        config.model.to.characteristic.ToStringCharacteristicValue.class,
                                        config.model.to.characteristic.ToMultipleCharacteristicValue.class));

                mapping(FromMultipleCharacteristicValue.class, ToMultipleCharacteristicValue.class,
                        TypeMappingOptions.oneWay())
                        .fields("value", "value",
                                hintA(config.model.from.characteristic.FromBigIntegerCharacteristicValue.class,
                                        config.model.from.characteristic.FromStringCharacteristicValue.class,
                                        config.model.from.characteristic.FromMultipleCharacteristicValue.class),
                                hintB(config.model.to.characteristic.ToBigIntegerCharacteristicValue.class,
                                        config.model.to.characteristic.ToStringCharacteristicValue.class,
                                        config.model.to.characteristic.ToMultipleCharacteristicValue.class));
            }
        };

        mapper = DozerBeanMapperBuilder.create()
                .withMappingBuilder(builder)
                .build();
    }

    protected boolean checkFromClass(Object fromObject, Object toObject){
        Class<?> fromObjectClass = fromObject.getClass();
        if (!fromObjectClass.getName().startsWith("config.model.from"))
            throw new IllegalArgumentException("FROM_object is incorrect with class' name: {" + fromObjectClass + "}");

        Class<?> toObjectClass = toObject.getClass();
        if (!toObjectClass.getName().startsWith("config.model.to"))
            throw new IllegalArgumentException("TO_object is incorrect with class' name: {" + toObjectClass + "}");

        Class resultClass = oeMapping.get(fromObjectClass);
        if (resultClass == null)
            throw new IllegalArgumentException("class with name: {" + toObjectClass + "} is not mapping in dozer configuration!");
        return resultClass.equals(toObjectClass);
    }
}
