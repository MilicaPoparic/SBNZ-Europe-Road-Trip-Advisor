package com.sbnz.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {
	
	private KieServices getKieServices() {
        return KieServices.Factory.get();
    }
	
	@Bean
    public KieContainer getKieContainer() {
        return getKieServices().getKieClasspathContainer();
    }
    
}
