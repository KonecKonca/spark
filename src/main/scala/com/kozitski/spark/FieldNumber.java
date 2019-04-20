package com.kozitski.spark;

import lombok.Getter;

public enum FieldNumber {
    ID(0, null),
    DATE_TIME(1, 0),
    SITE_NAME(2, 1),
    POSA_CONTINENT(3, 2),
    USER_LOCATION_COUNTRY(4, 3),
    USER_LOCATION_REGION(5, 4),
    USER_LOCATION_CITY(6, 5),
    ORIG_DESTINATION_DISTANCE(7, 6),
    USER_ID(8, 7),
    IS_MOBILE(9, 8),
    IS_PACKAGE(10, 9),
    CHANNEL(11, 10),
    SRCH_CI(12, 11),
    SRCH_CO(13, 12),
    SRCH_ADULTS_CNT(14, 13),
    SRCH_CHILDREN_CNT(15, 14),
    SRCH_RM_CNT(16, 15),
    SRCH_DESTINATION_ID(17, 16),
    SRCH_DESTINATION_TYPE_ID(18, 17),
    IS_BOOKING(null, 18),
    CNT(null, 19),
    HOTEL_CONTINENT(19, 20),
    HOTEL_COUNTRY(20, 21),
    HOTEL_MARKET(21, 22),
    HOTEL_CLUSTER(null, 23);

    @Getter private Integer test;
    @Getter private Integer train;

    FieldNumber(Integer test, Integer train) {
        this.test = test;
        this.train = train;
    }

}
