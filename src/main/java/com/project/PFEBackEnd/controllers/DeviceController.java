package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.Device;
import com.project.PFEBackEnd.entities.dto.DeviceCount;
import com.project.PFEBackEnd.entities.dto.DeviceCountByStatus;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatusMaterial;
import com.project.PFEBackEnd.services.IDeviceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin("*")

@RestController
@RequestMapping("/devices")
public class DeviceController {
private IDeviceService deviceService;

    public DeviceController(IDeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device createdDevice = deviceService.createDevice(device);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Device> updateDevice(@RequestBody Device device , @PathVariable long id) {
        Device updatedDevice = deviceService.updateDevice(device , id);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Long id) {
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getDevice/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable("id") Long id) {
        Device device = deviceService.findById(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
    @GetMapping("/countByDeviceCategory")
    public ResponseEntity<List<DeviceCountByStatus>> countDevicesByCategoryAndStatus() {
        List<DeviceCountByStatus> deviceCountList = deviceService.getCountByCategoryAndStatus();
        return new ResponseEntity<>(deviceCountList, HttpStatus.OK);
    }

    @GetMapping("/get-all-deviceType")
    @ResponseBody
    public DeviceType[] getDeviceType() {
        return DeviceType.values();
    }

    @GetMapping("/get-all-status")
    @ResponseBody
    public StatusMaterial[] getDeviceStatus() {
        return StatusMaterial.values();
    }
}

