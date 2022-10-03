package com.zwidek.customer.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerInfoContributor implements InfoContributor {

    private final BuildProperties buildProperties;

    private final String APP_NAME = "CUSTOMER";

    @Autowired
    public CustomerInfoContributor(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", APP_NAME);
        builder.withDetail("build", getBuildInfo());
    }

    private Map<String, Object> getBuildInfo() {
        Map<String, Object> buildContent = new HashMap<>();
        buildContent.put("version", buildProperties.getVersion());
        buildContent.put("timestamp", buildProperties.getTime());
        return buildContent;
    }
}
