package com.example.springboot_education.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClassMemberId implements Serializable {
    private Long classId;
    private Long memberId;

    public ClassMemberId() {}

    public ClassMemberId(Long classId, Long memberId) {
        this.classId = classId;
        this.memberId = memberId;
    }

    // Getters and setters
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassMemberId)) return false;
        ClassMemberId that = (ClassMemberId) o;
        return Objects.equals(classId, that.classId) &&
               Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, memberId);
    }
}
