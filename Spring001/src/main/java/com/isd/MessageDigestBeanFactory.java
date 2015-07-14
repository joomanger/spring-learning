package com.isd;

import java.security.MessageDigest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class MessageDigestBeanFactory implements FactoryBean<MessageDigest>, InitializingBean {
	private MessageDigest messageDigest = null;
	private String algorithm = null;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		messageDigest = MessageDigest.getInstance(algorithm);
	}

	@Override
	public MessageDigest getObject() throws Exception {
		// TODO Auto-generated method stub
		return messageDigest;
	}

	@Override
	public Class<MessageDigest> getObjectType() {
		// TODO Auto-generated method stub
		return MessageDigest.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
