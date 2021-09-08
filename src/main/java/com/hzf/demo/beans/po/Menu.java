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
@Table(name = "menu", schema = "demo")
@NamedEntityGraph(name = "MenuEntityGraph",
        attributeNodes = {@NamedAttributeNode(value = "authorities", subgraph = "RoleUserEntityGraph")},
        subgraphs = {@NamedSubgraph(name = "RoleUserEntityGraph", attributeNodes = {@NamedAttributeNode("users")})})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(schema = "demo", name = "role_menu", joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @ToString.Exclude
    @JsonIgnoreProperties(value = "menus")
    private Set<Role> authorities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Menu menu = (Menu) o;

        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return 1525026887;
    }
}
