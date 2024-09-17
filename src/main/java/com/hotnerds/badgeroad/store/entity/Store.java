package com.hotnerds.badgeroad.store.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Store {

    @Id
    private Long management_number;

    @Column
    private String store_name;

    @Column
    private String location_code;

    @Column
    private Integer category_code;

    @Column
    private String category_name;

    @Column
    private Integer industry_code;

    @Column
    private String industry_name;

    @Column
    private Integer food_authentication_num;

    @Column
    private String food_authentication_name;

    @Column
    private Double map_y;

    @Column
    private Double map_x;

    @Column
    private String phone_number;

    @Column
    private String address;

    @Column
    @ColumnDefault("0")
    private Integer liked;
}
