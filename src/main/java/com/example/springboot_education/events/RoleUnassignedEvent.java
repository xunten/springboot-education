package com.example.springboot_education.events;

import lombok.Value;

@Value
public class RoleUnassignedEvent {
    private final Long userId;
    private final Long roleId;
}
