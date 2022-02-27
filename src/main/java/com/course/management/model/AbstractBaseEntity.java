package com.course.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created", "modified"})
@NoArgsConstructor
public class AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(updatable = false)
    private LocalDateTime created;
    @Column(insertable = true, updatable = false)
    private LocalDateTime modified;

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBaseEntity)) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals (id, that.id) && Objects.equals (created, that.created) && Objects.equals (modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, created, modified);
    }
}
