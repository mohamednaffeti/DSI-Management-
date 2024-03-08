package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Device;
import com.project.PFEBackEnd.entities.dto.DeviceCount;
import com.project.PFEBackEnd.entities.dto.DeviceCountByStatus;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatusMaterial;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.exceptions.DeviceExisteWithSerialNumberException;
import com.project.PFEBackEnd.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements IDeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device createDevice(Device device) {
        Device device1 = deviceRepository.getDeviceBySerialNumber(device.getSerialNumber());
        if(device1!=null){
            throw new DeviceExisteWithSerialNumberException("Device with this serial Number already exist");
        }else{
            return deviceRepository.save(device);
        }

    }

    @Override
    public Device updateDevice(Device device , long id) {
        Device existingDevice  = deviceRepository.findById(id).orElse(null);
        if(existingDevice ==null){
            throw new DataNotFoundException("Device not found");
        }else{
            existingDevice.setSerialNumber(device.getSerialNumber());
            existingDevice.setDeviceName(device.getDeviceName());
            existingDevice.setCategory(device.getCategory());
            existingDevice.setStatut(device.getStatut());
            return deviceRepository.save(existingDevice);

        }
    }

    @Override
    public void deleteDevice(Long id) {
            deviceRepository.deleteById(id);
    }

    @Override
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeviceCountByStatus> getCountByCategoryAndStatus() {
        List<Object[]> results = deviceRepository.countDevicesByCategoryAndStatus();

        Map<DeviceType, Map<StatusMaterial, Integer>> deviceCounts = new HashMap<>();
        Map<DeviceType, Integer> categoryCounts = new HashMap<>();

        for (Object[] result : results) {
            DeviceType category = (DeviceType) result[0];
            StatusMaterial status = (StatusMaterial) result[1];
            Long count = (Long) result[2];

            deviceCounts.putIfAbsent(category, new HashMap<>());
            deviceCounts.get(category).put(status, count.intValue());


            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + count.intValue());
        }

        List<DeviceCountByStatus> deviceCountList = new ArrayList<>();
        for (Map.Entry<DeviceType, Map<StatusMaterial, Integer>> entry : deviceCounts.entrySet()) {
            DeviceType category = entry.getKey();
            Map<StatusMaterial, Integer> statusCounts = entry.getValue();
            int totalCount = categoryCounts.getOrDefault(category, 0);

            DeviceCountByStatus deviceCountByStatus = new DeviceCountByStatus(category.name(), totalCount, statusCounts);
            deviceCountList.add(deviceCountByStatus);
        }

        return deviceCountList;
    }




}
