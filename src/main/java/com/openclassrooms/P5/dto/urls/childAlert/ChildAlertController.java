package com.openclassrooms.P5.dto.urls.childAlert;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChildAlertController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/childAlert")
    public ResponseEntity<?> childAlertByAddress(@RequestParam String address) {
        List<Child> childAlertList = personServiceImpl.childAlertByAddress(address);
        log.info("get the child list for alert address = {}", address);

        return ResponseEntity.status(HttpStatus.OK).body(childAlertList);
    }
}
