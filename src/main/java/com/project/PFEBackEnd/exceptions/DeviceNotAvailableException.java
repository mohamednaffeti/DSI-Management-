package com.project.PFEBackEnd.exceptions;

public class DeviceNotAvailableException extends RuntimeException{
    public DeviceNotAvailableException(String message) {
        super(message);
    }
}
