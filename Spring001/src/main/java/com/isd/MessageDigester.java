package com.isd;

import java.security.MessageDigest;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("digester")
public class MessageDigester {

	private MessageDigest digest1 = null;
	private MessageDigest digest2 = null;

	private String msg = null;

	@Resource(name = "shaDigest")
	public void setDigest1(MessageDigest digest1) {
		this.digest1 = digest1;
	}

	@Resource(name = "md5Digest")
	public void setDigest2(MessageDigest digest2) {
		this.digest2 = digest2;
	}

	public void digest(String msg) {
		this.msg = msg;
		showDigest(digest1);
		showDigest(digest2);

	}

	private void showDigest(MessageDigest digest) {
		digest.reset();
		byte[] bytes = msg.getBytes();
		byte[] out = digest.digest(bytes);
		System.out.println("Algorithm [" + digest.getAlgorithm() + "]" + out);
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < out.length; i++) {
			hexString.append(Integer.toHexString(0xFF & out[i]));
		}
		System.out.println(hexString.toString());
	}

}
