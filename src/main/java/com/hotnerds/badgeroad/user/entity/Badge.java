package com.hotnerds.badgeroad.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "badge")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Badge {

    @Id
    @Column(name = "badge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "location", length = 10)
    private String location;

    @Column(name = "category", length = 20)
    private String category;
}
