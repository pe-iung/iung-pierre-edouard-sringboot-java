package com.openclassrooms.P5.dto.urls.phoneAlert;

import com.openclassrooms.P5.dto.person.Child;
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
public class PhoneAlertController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/phoneAlert")
    public ResponseEntity<?> phoneAlertByFirestationId(@RequestParam Integer firestation) {
        List<String> phoneAlertList = personServiceImpl.phoneAlertByFirestationID(firestation);
        return ResponseEntity.status(HttpStatus.OK).body(phoneAlertList);
    }
}