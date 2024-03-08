package com.project.PFEBackEnd.exceptions;

public class DeviceExisteWithSerialNumberException extends RuntimeException{
    public DeviceExisteWithSerialNumberException(String message) {
        super(message);
    }
}
