package com.enterprise.attendance.exception;

@SuppressWarnings("serial")
public class NullException extends RuntimeException {
    public NullException(String message)
    {
        super(message);
    }
}