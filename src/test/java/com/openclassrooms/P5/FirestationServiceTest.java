package com.openclassrooms.P5;

import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import com.openclassrooms.P5.service.FirestationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {

	@Mock
	private static FirestationRepository firestationRepository;

	@InjectMocks
	private FirestationServiceImpl firestationService;

	@Test
	void testFirestationSave() {
		//given a firestation
		final String address = "stationTest";
		final Firestation firestationToSave = new Firestation(address, 1);

		//when saving a firestation in FirestationService
		firestationService.saveFirestation(firestationToSave);

		// then the station is added to the repository
		verify(firestationRepository, times(1)).addFirestation(firestationToSave);


	}
}
