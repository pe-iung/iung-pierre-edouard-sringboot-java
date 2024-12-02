package com.openclassrooms.P5.dto.urls.childAlert;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.service.FirestationServiceImpl;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChildAlertController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/childAlert")
    public ResponseEntity<?> childAlertByAddress(@PathVariable String address) {
        List<Child> childAlertList = personServiceImpl.childAlertByAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(childAlertList);
    }
}