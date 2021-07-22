package com.sxd.swapping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 区分 es的bean 和  Jpa的bean
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.sxd.swapping.jpa")
@EnableElasticsearchRepositories(basePackages = "com.sxd.swapping.es")
public class ElasticSearchConfig {

}
