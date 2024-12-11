package com.openclassrooms.P5.dto.urls.communityEmail;

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
public class CommunityEmailController {
    private final PersonServiceImpl personServiceImpl;

    @GetMapping("/communityEmail")
    public ResponseEntity<?> communityEmail(@RequestParam String city) {
        List<String> citizenEmailList = personServiceImpl.citizenEmailListByCity(city);
        log.info("get the citizen email list by city = {}", city);
        return ResponseEntity.status(HttpStatus.OK).body(citizenEmailList);
    }
}
