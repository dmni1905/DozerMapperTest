import config.model.FromOrder;
import config.model.ToOrder;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class DozerMapTest {

    private static Mapper mapper;
    private Map<Class, Class> oeMapping = new HashMap<>();
    {
        oeMapping.put(config.model.FromOrder.class, config.model.ToOrder.class);
    }
    private BigInteger id = BigInteger.ONE;


    @BeforeClass
    public static void before() throws Exception {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(FromOrder.class, ToOrder.class,
                        TypeMappingOptions.oneWay())
                        .fields("fromItems", "toItems");
            }
        };

        mapper = DozerBeanMapperBuilder.create()
                .withMappingBuilder(builder)
                .build();
    }

    @Test
    public void test() throws IOException{
        FromOrder from = new FromOrder();

        Map<BigInteger, Set<BigInteger>> items = new HashMap<>();
        Set<BigInteger> itValues = new HashSet<>();
        itValues.add(BigInteger.TEN);
        items.put(id, itValues);
        from.setFromItems(items);

        ToOrder to = mapper.map(from, ToOrder.class);
        assertTrue(!to.getToItems().get(id).isEmpty());
    }
}
