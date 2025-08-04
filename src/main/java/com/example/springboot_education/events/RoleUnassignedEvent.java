package com.example.springboot_education.events;

import org.springframework.context.ApplicationEvent;

public class RoleUnassignedEvent extends ApplicationEvent {
    private final Long userId;
    private final Long roleId;

    public RoleUnassignedEvent(Long userId, Long roleId) {
        super(userId);
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() { return userId; }
    public Long getRoleId() { return roleId; }
}
