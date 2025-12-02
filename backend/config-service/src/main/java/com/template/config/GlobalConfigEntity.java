package com.template.config;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "global_config")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfigEntity {
    @Id
    private String id; // usually "default"
    private String appName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private String timezone;
}
