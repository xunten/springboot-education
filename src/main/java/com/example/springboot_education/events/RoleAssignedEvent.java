package com.example.springboot_education.events;

import lombok.Value;

@Value
public class RoleAssignedEvent {
    private final Long userId;
    private final Long roleId;
}
