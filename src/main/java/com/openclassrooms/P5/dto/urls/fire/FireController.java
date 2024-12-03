package com.openclassrooms.P5.dto.urls.fire;

import com.openclassrooms.P5.dto.person.PersonWithPhoneAgeMedicationsAllergies;
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
public class FireController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/fire")
    public ResponseEntity<?> personLivingAtAddress(@RequestParam String address) {
        List<PersonWithPhoneAgeMedicationsAllergies> personWithPhoneAgeMedicationsAllergies = personServiceImpl.personLivingAtAddress(address);
        log.info("get the list of PersonWithPhoneAgeMedicationsAllergies by address = {}", address);
        return ResponseEntity.status(HttpStatus.OK).body(personWithPhoneAgeMedicationsAllergies);
    }
}
