package com.trip.busi.service;

import com.talkweb.commons.core.service.BaseService;
import com.trip.busi.entity.VisaAttachment;
import com.trip.busi.dao.VisaAttachmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理
 * @author 玲玲
 * @create 2019-05-30 21:43:00
 */

@Service
public class VisaAttachmentService extends BaseService<VisaAttachment> {

    @Autowired
    private VisaAttachmentDao visaAttachmentDao;


}
