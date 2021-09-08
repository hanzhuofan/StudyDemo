package com.hzf.demo.beans.domain;

import java.util.Objects;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "role", schema = "demo")
public class RoleDO implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Override
    public String getAuthority() {
        return getId().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RoleDO roleDO = (RoleDO)o;

        return Objects.equals(id, roleDO.id);
    }

    @Override
    public int hashCode() {
        return 1179619963;
    }
}
