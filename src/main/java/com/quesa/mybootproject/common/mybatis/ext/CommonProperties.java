package com.quesa.mybootproject.common.mybatis.ext;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;

@ConfigurationProperties(prefix = "mybatis.ext")
@Component
public class CommonProperties {

    private String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    private String mapperPackage;
    private String entityPackage;

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public Resource[] resolveMapperPackage() throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                org.springframework.util.ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(mapperPackage)) + "/" + DEFAULT_RESOURCE_PATTERN;
        return new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
    }

    public Resource[] resolveEntityPackage() throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                org.springframework.util.ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(entityPackage)) + "/" + DEFAULT_RESOURCE_PATTERN;
        return new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
    }
}
