package com.hotnerds.badgeroad.user.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class User {

   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;

   @Column(name = "email", length = 50, unique = true)
   @Email
   private String email;

   @Column(name = "password", length = 100)
   private String password;

   @Column(name = "name", length = 50)
   private String name;

   @Column(name = "level")
   @ColumnDefault("1")
   private Integer level;

   @OneToMany
   @JoinTable(
           name = "user_badges",
           joinColumns = {@JoinColumn(name = "email", referencedColumnName = "email")},
           inverseJoinColumns = {@JoinColumn(name = "badge_name", referencedColumnName = "name")}
   )
   private Set<Badge> badges;
}
