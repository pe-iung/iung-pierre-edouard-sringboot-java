package com.openclassrooms.P5.dto.urls.flood;

import com.openclassrooms.P5.dto.person.Home;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class FloodController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/flood/stations")
    public ResponseEntity<?> homesCloseToStations(@RequestParam List<Integer> stations) {
        var homes = personServiceImpl.homesByStation(stations);

        log.info("get the people (by adresse) living close to the station numbe list ={}", stations);
        return ResponseEntity.status(HttpStatus.OK).body(homes);
    }
}
