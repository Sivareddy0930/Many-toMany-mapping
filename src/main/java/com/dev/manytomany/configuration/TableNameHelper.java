package com.dev.manytomany.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TableNameHelper {

    @Value("${spring.entity.table.mapping.name}")
    private String tableName;

    public String getTableName() {
        return tableName;
    }
}

