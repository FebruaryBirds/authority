package com.common.constant;

public enum UserGenderEnum {
    FEMALE(0, "女"),
    MALE(1, "男");

    private Integer id;
    private String name;

    UserGenderEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender(Integer id) {
        if (UserGenderEnum.FEMALE.id.equals(id)) {
            return UserGenderEnum.FEMALE.name;
        }
        return UserGenderEnum.MALE.name;
    }
}
