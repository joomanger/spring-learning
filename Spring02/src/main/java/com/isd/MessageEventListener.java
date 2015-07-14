package com.isd;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class MessageEventListener implements ApplicationListener<MessageEvent> {

	@Override
	public void onApplicationEvent(MessageEvent event) {
		// TODO Auto-generated method stub
		System.out.println("received: " + event.getMessage()+" "+event.getSource());
	}

}
