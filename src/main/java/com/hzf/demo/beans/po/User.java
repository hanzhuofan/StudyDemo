package com.hzf.demo.beans.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

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
@NamedEntityGraph(name = "UserEntityGraph",
    attributeNodes = {@NamedAttributeNode(value = "authorities", subgraph = "RoleMenuEntityGraph")},
    subgraphs = {@NamedSubgraph(name = "RoleMenuEntityGraph", attributeNodes = {@NamedAttributeNode("menus")})})
public class User {
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

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(schema = "demo", name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @ToString.Exclude
    @JsonIgnoreProperties(value = "users")
    private Set<Role> authorities = new HashSet<>();

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

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
        User user = (User)o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
