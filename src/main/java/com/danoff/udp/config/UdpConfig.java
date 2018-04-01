package com.danoff.udp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.MessageChannel;

import com.danoff.udp.service.UdpInboundMessageHandler;

@Configuration
public class UdpConfig {

	@Value("${udp.channel}")
	private String channel;
	@Value("${udp.port}")
	private Integer port;
	
    @Bean
    public UdpInboundMessageHandler udpHandler() {
        return new UdpInboundMessageHandler();
    }
    
	@Bean
	public UnicastSendingMessageHandler udpSendingAdapter() {
		return new UnicastSendingMessageHandler("localhost", port);
	}

    @Bean
    public IntegrationFlow processUdpMessage() {
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(port))
                .handle(udpHandler(), "handeMessage")
                .get();
    }

}
