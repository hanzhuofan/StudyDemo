package com.hzf.demo.beans.po;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user", schema = "demo")
public class SimpleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired", columnDefinition = "boolean default true")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", columnDefinition = "boolean default true")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", columnDefinition = "boolean default true")
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private Boolean enabled = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        SimpleUser that = (SimpleUser) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1486489462;
    }
}
