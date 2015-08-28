package com.equivi.channel.tracker;


import com.equivi.channel.dto.CampaignStatisticDTO;
import com.equivi.mailsy.data.entity.CampaignTrackerEntity;

import java.util.List;
import java.util.Map;

public interface CampaignTrackerService {


    void createCampaignTracker(String externalMessageId, Long campaignId, String recipient);

    CampaignTrackerEntity getCampaignTrackerEntity(Long campaignTrackerId);

    void saveCampaignTrackerEntity(CampaignTrackerEntity campaignTrackerEntity);

    List<CampaignTrackerEntity> getCampaignTrackerEntityList(Map<CampaignTrackerSearchFilter, String> campaignTrackerSearchFilterStringMap);

    CampaignStatisticDTO getCampaignStatistic(Long campaignId);
}
