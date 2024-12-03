package com.openclassrooms.P5.dto.urls.stationNumber;

import com.openclassrooms.P5.dto.person.PersonListAndCountByStationNumber;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PeopleAtStationNumberController {

    private final PersonServiceImpl personService;

    @GetMapping("/firestation")
    public ResponseEntity<?> peopleAtStationNumber(@RequestParam int stationNumber) {
        PersonListAndCountByStationNumber personListAndCount =
                personService.getPersonsListAndCountByStationNumber(stationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(personListAndCount);
    }

}
