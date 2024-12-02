package com.openclassrooms.P5.dto.urls.personInfolastName;

import com.openclassrooms.P5.dto.person.PersonInfoLastName;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class personInfoLastNameController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/personInfoLastNameController")
    public ResponseEntity<?> personInfoLastName(@RequestParam String lastname) {
        List<PersonInfoLastName> personInfoLastNames = personServiceImpl.personsInfoByLastName(lastname);
        return ResponseEntity.status(HttpStatus.OK).body(personInfoLastNames);
    }
}
