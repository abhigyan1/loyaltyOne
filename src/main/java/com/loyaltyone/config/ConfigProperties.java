package com.loyaltyone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "temperature")
public class ConfigProperties {
private String key;
private String metric;

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}

public String getMetric() {
	return metric;
}

public void setMetric(String metric) {
	this.metric = metric;
}

	
}
