package config;

import org.dozer.config.BeanContainer;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.util.DefaultClassLoader;

public class OEMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        BeanContainer.getInstance().setClassLoader(new DefaultClassLoader(Thread.currentThread().getContextClassLoader()));
        projectConfig();
    }

    protected void projectConfig() {
        mapping(config.model.FromOrder.class, config.model.ToOrder.class)
                .fields("fromItems", "toItems");
    }
}
