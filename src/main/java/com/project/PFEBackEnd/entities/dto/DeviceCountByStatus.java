package com.project.PFEBackEnd.entities.dto;

import com.project.PFEBackEnd.entities.enumerations.StatusMaterial;
import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCountByStatus {
    private String category;
    private int count;
    private Map<StatusMaterial, Integer> statusCounts;
}
