package com.hotnerds.badgeroad.user.dto;

import javax.validation.constraints.NotNull;

public class BadgeDto {

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String category;
}
