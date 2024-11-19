package com.openclassrooms.P5;

import com.openclassrooms.P5.model.Firestation;
import com.openclassrooms.P5.repository.FirestationRepository;
import com.openclassrooms.P5.service.FirestationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

	@Test
	void testDeleteFirestationByAddress() {
		//given a firestation
		final String address = "stationTest";

		//when deleting a firestation in FirestationService
		firestationService.deleteFirestationByAddress(address);

		// then the station is deleted from to the repository
		verify(firestationRepository, times(1)).deleteFirestationByAddress(address);
	}

	@Test
	void testgetFirestationByAdress() {
		//given a firestation
		final String address = "stationTest";

		//when deleting a firestation in FirestationService
		firestationService.getFirestationByAdress(address);

		// then the station is deleted from to the repository
		verify(firestationRepository, times(1)).findFirestationByAddress(address);

	}

	//TODO fix unit test issue for update
	@Test
	void testupdateFirestation() {

		//given a firestation
		final String address = "stationTest";
		final Firestation oldFirestation = new Firestation(address, 1);
		final Firestation newFirestation = new Firestation(address, 2);
		final int index = 0;

		//when updating a firestation in FirestationService
		when(firestationRepository.getFirestations()).thenReturn(List.of(oldFirestation));
		firestationService.updateFirestation(oldFirestation,newFirestation);

		// then the station is deleted from to the repository
		verify(firestationRepository, times(1)).getFirestations();


	}
}
