package com.weiyiysw.spring.example.codes.interceptors;

import com.weiyiysw.spring.example.codes.wrapper.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yishiwei
 * @date 2019/9/5
 */
@Component
public class ReqResLoggerInterceptors extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReqResLoggerInterceptors.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder builder = new StringBuilder();
        if (request instanceof RequestWrapper) {
            builder.append(((RequestWrapper) request).getBody());
        } else {
            RequestWrapper wrapper = new RequestWrapper(request);
            builder.append(wrapper.getBody());
        }
        LOGGER.info("method: {}, request uri: {}, request body: {}", request.getMethod(), request.getRequestURI(), builder.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LOGGER.info("response status {}", response.getStatus());
    }
}
