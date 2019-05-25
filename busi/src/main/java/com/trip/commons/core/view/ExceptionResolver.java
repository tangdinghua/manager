package com.trip.commons.core.view;

import com.trip.commons.core.annotation.ErrorPage;
import com.trip.commons.core.annotation.ErrorPages;
import com.trip.commons.core.utils.RequestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 统一错误处理
 * <p>
 *     1、如果是Ajax请求，则使用json视图输出。
 *     2、如果配置了错误页面，则指向错误页面，否则使用默认错误页面
 * </p>
 * @author fqh
 * @create 2016-12-10 1:15
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        DataOutPack pack = new DataOutPack(e);
        if(RequestUtils.isAjax(request)) {
            return new ModelAndView("jsonView", "data", pack);
        }
        ErrorPage errorPage = getErrorPage(handler, pack.getCode());
        String page = "/error";
        if(errorPage != null) {
            page = errorPage.page();
        }
        pack.put("code", pack.getCode());
        pack.put("msg", pack.getMessage());
        return new ModelAndView(page, pack);
    }

    /**
     * 获取错误配置的页面
     * @param handler
     * @param code
     * @return
     */
    private ErrorPage getErrorPage(Object handler, String code) {
        if(handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            ErrorPages errorPages = method.getAnnotation(ErrorPages.class);
            if(errorPages == null) {
                return method.getAnnotation(ErrorPage.class);
            }
            for(ErrorPage errorPage : errorPages.value()) {
                if(code.equals(errorPage.code())) {
                    return errorPage;
                }
            }
        }
        return null;
    }
}
