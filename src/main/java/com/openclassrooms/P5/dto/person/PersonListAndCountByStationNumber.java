package com.openclassrooms.P5.dto.person;

import java.util.List;

public record PersonListAndCountByStationNumber(
        List<PersonWithPhoneAndAddress> personWithPhoneAndAddressLists,
        int counterAdult,
        int counterMinor) {

}
