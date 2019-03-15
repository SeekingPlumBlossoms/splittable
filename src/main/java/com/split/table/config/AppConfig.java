package com.split.table.config;

import com.split.table.fliter.MybatisSqlInterceptor;
import com.split.table.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * @author yebin
 * @version 1.0
 * @className AppConfig
 * @description peizhi
 * @date 2019/3/14 16:33
 **/
@Configuration
public class AppConfig {
    private static String cfgPrefix = "yb.split.table";
    private static String ruleCfgPrefix = "yb.split.celv.table.cl";
    @Autowired
    List<Strategy> strategyList;

    @Autowired
    Environment env;

    @PostConstruct
    public void appConfigInit() {
        AbstractEnvironment aEnv = (AbstractEnvironment) env;
        MutablePropertySources propertySources = aEnv.getPropertySources();
        propertySources.forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mps = (MapPropertySource) propertySource;
                Set<String> keys = mps.getSource().keySet();
                for (String key : keys) {
                    if (key.startsWith(cfgPrefix)) {
                        MybatisSqlInterceptor.topicSet.add(String.valueOf(mps.getProperty(key)));
                    } else if (key.startsWith(ruleCfgPrefix)) {
                        String[] keysArr=key.split("\\.");
                        System.out.println(key);
                        System.out.println(keysArr.length);
                        MybatisSqlInterceptor.ruleMap.put(keysArr[keysArr.length-1], String.valueOf(mps.getProperty(key)));
                    }
                }
            }
        });
        strategyList.stream().forEach((strategy -> {
            System.out.println(strategy.getClass().getName());
            MybatisSqlInterceptor.tbaleMap.put(strategy.getClass().getName(), strategy);
        }));


    }
}
