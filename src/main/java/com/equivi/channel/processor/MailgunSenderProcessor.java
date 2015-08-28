package com.equivi.channel.processor;

import com.equivi.channel.dto.CampaignEmailObject;
import com.equivi.channel.dto.CampaignSubscriberList;
import com.equivi.channel.mailgun.MailgunService;
import com.equivi.channel.tracker.CampaignTrackerService;
import com.google.common.collect.Lists;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailgunSenderProcessor implements Processor {

    @Autowired
    @Qualifier("mailgunJerseyService")
    private MailgunService mailgunJerseyService;

    @Autowired
    private CampaignTrackerService campaignTrackerService;

    @Override
    public void process(Exchange exchange) throws Exception {
        CampaignSubscriberList campaignSubscriberList = (CampaignSubscriberList) exchange.getIn().getBody();

        List<CampaignEmailObject> campaignEmailObjectList=campaignSubscriberList.getCampaignEmailObjectList();

        for(CampaignEmailObject campaignEmailObject:campaignEmailObjectList){
            sendAndTrackEmailToMailgun(campaignEmailObject);
        }

    }

    private void sendAndTrackEmailToMailgun(CampaignEmailObject campaignEmailObject) {
        for(String emailAddress:campaignEmailObject.getEmailList()){
            String messageId = mailgunJerseyService.sendMessageWithAttachment(campaignEmailObject.getCampaignUUID(), null, campaignEmailObject.getEmailFrom(), Lists.newArrayList(emailAddress), null, null,
                    StringEscapeUtils.unescapeHtml4(campaignEmailObject.getEmailSubject()), campaignEmailObject.getEmailContent());

            if (!StringUtils.isEmpty(messageId)) {
                campaignTrackerService.createCampaignTracker(messageId, campaignEmailObject.getCampaignId(), emailAddress);
            }
        }
    }
}
