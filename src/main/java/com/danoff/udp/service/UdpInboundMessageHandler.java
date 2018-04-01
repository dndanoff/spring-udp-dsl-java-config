package com.danoff.udp.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;

public class UdpInboundMessageHandler {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpInboundMessageHandler.class);
	
	public void handeMessage(Message message, @Headers Map<String, Object> headerMap) {
		LOGGER.info("Received UDP message: {}", new String(new String((byte[]) message.getPayload())));
	}
}
