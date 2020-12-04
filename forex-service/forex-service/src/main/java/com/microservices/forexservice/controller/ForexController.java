package com.microservices.forexservice.controller;

import com.microservices.forexservice.model.ExchangeValue;
import com.microservices.forexservice.repository.ExchangeValueRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ForexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private String port;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/forex-exchange/from/{from}/to/{to}")
    @HystrixCommand(fallbackMethod = "fallbackMethodRetrieveExchangeValue")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        hystrixDemostration();

        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(port));

        logger.info("RESPONSE= {}", exchangeValue);
        return exchangeValue;
    }

    // e.g. - database is not available
    private void hystrixDemostration() {
        throw new RuntimeException("Not available!");
    }

    private ExchangeValue fallbackMethodRetrieveExchangeValue(String from, String to) {
        ExchangeValue exchangeValue = new ExchangeValue(0L, from, to, new BigDecimal(0));
        exchangeValue.setPort(Integer.parseInt(port));

        logger.info("RESPONSE= {}", exchangeValue);
        return exchangeValue;
    }
}