package com.equivi.channel.processor;

import com.equivi.channel.dto.CampaignSubscriberList;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeliveryChannelRouter extends SpringRouteBuilder {

    @Autowired
    private MailgunSenderProcessor mailgunSenderProcessor;

    @Override
    public void configure() throws Exception {
        JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(CampaignSubscriberList.class);
        jacksonDataFormat.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        from("activemq:queue:Q_PROCESSOR_TO_EMAILCHANNEL")
                .unmarshal(jacksonDataFormat)
                .process(mailgunSenderProcessor);
    }
}
