package com.example.springboot_education.events;

import org.springframework.context.ApplicationEvent;

public class RoleAssignedEvent extends ApplicationEvent {
    private final Long userId;
    private final Long roleId;

    public RoleAssignedEvent(Long userId, Long roleId) {
        super(userId);
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() { return userId; }
    public Long getRoleId() { return roleId; }
}