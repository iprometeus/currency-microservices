package com.microservices.zuulapigatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // define if we want to execute filter before or after request is executed, or only on errors
    @Override
    public String filterType() {
        return "pre";
    }

    // priority of the filter if there are couple filters
    @Override
    public int filterOrder() {
        return 1;
    }

    // should this filter be executed or not. you can write conditions here, so for some requests it won't be executed
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // main logic of filter
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {}, uri -> {}", request, request.getRequestURI());
        return null;
    }
}
