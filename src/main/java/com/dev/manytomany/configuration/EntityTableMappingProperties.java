package com.dev.manytomany.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.entity")
@Getter
@Setter
public class EntityTableMappingProperties {
    private String tableMappingName;
}
