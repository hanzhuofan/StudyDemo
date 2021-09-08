package com.hzf.demo.beans.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
@NamedEntityGraph(name = "UserDOEntityGraph", attributeNodes = {@NamedAttributeNode(value = "authorities")})
public class UserDO implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired", columnDefinition = "boolean default true")
    private Boolean accountNonExpired = true;

    @Column(name = "account_non_locked", columnDefinition = "boolean default true")
    private Boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", columnDefinition = "boolean default true")
    private Boolean credentialsNonExpired = true;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private Boolean enabled = true;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(schema = "demo", name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<RoleDO> authorities = new HashSet<>();

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UserDO userDO = (UserDO)o;

        return Objects.equals(id, userDO.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
