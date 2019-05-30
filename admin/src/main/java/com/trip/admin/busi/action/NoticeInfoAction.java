package com.trip.admin.busi.action;

import com.trip.commons.core.service.BaseService;
import com.trip.base.action.BaseAction;
import com.trip.busi.entity.NoticeInfo;
import com.trip.busi.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/busi/noticeInfo")
public class NoticeInfoAction extends BaseAction<NoticeInfo> {
    @Autowired
    private NoticeInfoService noticeInfoService;

    @Override
        public BaseService getService() {
        return  noticeInfoService;
        }

}
