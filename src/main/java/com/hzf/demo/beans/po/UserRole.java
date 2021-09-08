package com.hzf.demo.beans.po;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.Hibernate;

import lombok.*;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_role", schema = "demo")
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UserRole userRole = (UserRole)o;

        return Objects.equals(id, userRole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Data
    @Embeddable
    public static class UserRoleId implements Serializable {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "role_id")
        private Long roleId;
    }
}
