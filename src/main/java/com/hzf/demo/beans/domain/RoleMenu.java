package com.hzf.demo.beans.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "role_menu", schema = "demo")
public class RoleMenu {
    @EmbeddedId
    private RoleMenuId id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RoleMenu roleMenu = (RoleMenu) o;

        return Objects.equals(id, roleMenu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Data
    @Embeddable
    public static class RoleMenuId implements Serializable {
        @Column(name = "role_id")
        private Long roleId;

        @Column(name = "menu_id")
        private Long menuId;
    }
}
