package ru.gnev.conciergebot.bean.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * Координаты квартиры в рамках дома
 */
public enum FlatCoordinate {
    ENTRANCE("entrance", "подъезд"),
    ADDRESS("address", "адрес"),
    FLOOR("floor", "этаж"),
    SECTION("section", "план"),
    FLAT("flat", "кв");

    private final String value;
    private final String meaning;

    FlatCoordinate(String value, String meaning) {
        this.value = value;
        this.meaning = meaning;
    }

    public String getValue() {
        return value;
    }

    public String getMeaning() {
        return meaning;
    }

    public static FlatCoordinate findByMeaning(String meaning) {
        if (StringUtils.isBlank(meaning)) return null;
        for (FlatCoordinate fc : FlatCoordinate.values()) {
            if (fc.meaning.equals(meaning)) return fc;
        }
        return null;
    }
}
