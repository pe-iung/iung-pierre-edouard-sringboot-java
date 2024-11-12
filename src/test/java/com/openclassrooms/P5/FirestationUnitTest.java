package com.openclassrooms.P5;

import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import com.openclassrooms.P5.service.FirestationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FirestationUnitTest {

	@Mock
	private static FirestationRepository firestationRepository;

	private FirestationServiceImpl firestationService;

	private final Firestation firestation = new Firestation("stationTest", 1);

	@Test
	void testFirestationSave() {
		//given a firestation

		//when saving a firestation in FirestationService
		FirestationServiceImpl firestationService = new FirestationServiceImpl(firestationRepository);
		firestationService.saveFirestation(firestation);

		// then the station is added to the repository
		verify(firestationRepository, times(1)).addFirestation(any(Firestation.class));
		assertNotNull(firestationRepository.getFirestationsByStation(1));

    }

}
