import config.OEMappingBuilder;
import config.model.FromOrder;
import config.model.ToOrder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

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
        mapper = new DozerBeanMapper();
        ((DozerBeanMapper) mapper).addMapping(new OEMappingBuilder());
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
