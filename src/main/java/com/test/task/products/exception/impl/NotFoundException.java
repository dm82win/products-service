package com.test.task.products.exception.impl;

import com.test.task.products.exception.CustomException;

public class NotFoundException extends CustomException {
    public NotFoundException(String message) {
        super(message);
    }
}
