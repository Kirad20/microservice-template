package com.template.config;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_preference")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceEntity {
    @Id
    private String userId;
    private String theme;
    private String language;
}
