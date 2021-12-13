package com.pngabo.belgorent.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "utilisateurs_role",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Column(nullable = false, name = "account_non_expired")
    private boolean accountNonExpired = true;
    @Column(nullable = false, name = "account_non_locked")
    private boolean accountNonLocked = true;
    @Column(nullable = false, name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;
    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    protected String nom;
    @Column(nullable = false)
    protected String prenom;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map((elm) -> new SimpleGrantedAuthority(elm.getNom()))
                .collect(Collectors.toList());
    }

    public static long calculAge(LocalDate bDay) {
        LocalDate toDay = LocalDate.now();

        if (bDay != null){
            long age = YEARS.between(bDay, toDay);
            return age;
        }

        return -1;
    }
}
