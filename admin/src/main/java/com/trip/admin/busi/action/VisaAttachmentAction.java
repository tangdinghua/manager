package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.VisaAttachment;
import com.trip.busi.service.VisaAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/visaAttachment")
public class VisaAttachmentAction extends BaseAction<VisaAttachment> {
    @Autowired
    private VisaAttachmentService visaAttachmentService;

    @Override
        public BaseService getService() {
        return  visaAttachmentService;
        }

}
