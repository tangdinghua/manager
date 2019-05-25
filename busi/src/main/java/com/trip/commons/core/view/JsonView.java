package com.trip.commons.core.view;

import com.trip.commons.core.utils.JsonUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * JSON输出视图，主要用于错误信息输出
 *
 * @author fqh
 * @create 2016-12-10 1:24
 */
public class JsonView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/plain;charset=UTF-8");
        DataOutPack pack = (DataOutPack) map.remove("data");
        if(pack == null) {
            response.getWriter().println(JsonUtils.toJson(map));
        } else {
            response.setHeader("code", pack.getCode());
            response.setHeader("msg", URLEncoder.encode(pack.getMessage(), "UTF-8"));
            pack.remove("code");
            pack.remove("msg");
            response.getWriter().println(JsonUtils.toJson(pack));
        }
    }
}
