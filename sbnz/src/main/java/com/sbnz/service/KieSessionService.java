package com.sbnz.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieSessionService {

	private static Logger log = LoggerFactory.getLogger(KieSessionService.class);

	private final KieContainer kieContainer;

	private KieSession cepSession;

	@Autowired
	public KieSessionService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public KieContainer getKieContainer() {
		return kieContainer;
	}

	public KieSession getCepSession() {
		if (this.cepSession == null) {
			log.info("Initialized session");
			cepSession = kieContainer.newKieSession("cepKsession");
		}
		return cepSession;
	}

	public void setCepSession(KieSession kieSession) {
		this.cepSession = kieSession;
	}

	public void releaseCepSession() {
		this.cepSession.dispose();
		this.cepSession = null;
	}

}