package com.weiyiysw.spring.example.codes.filters;

import com.weiyiysw.spring.example.codes.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yishiwei
 * @date 2019/9/5
 */
@Component
@Order(1)
public class RequestTimeLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper wrapper = null;
        if (request instanceof HttpServletRequest) {
            wrapper = new RequestWrapper((HttpServletRequest) request);
        }
        Long start = System.currentTimeMillis();
        if (wrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(wrapper, response);
        }
        LOGGER.info("cost time: {}.", System.currentTimeMillis() - start);
    }
}
