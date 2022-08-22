package com.citi.portfolio.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such trade")  // 404
public class TradeNotFoundException extends RuntimeException {

}
