package com.openclassrooms.P5.repository;

import com.openclassrooms.P5.model.Firestation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FirestationRepositoryTest {
    @Mock
    private static DataLoader dataLoader;

    @InjectMocks
    private FirestationRepositoryFromJson firestationRepositoryFromJson;

    @Test
    public void testfindFirestationWithGoodAddress() {

        //given an address
        final String address = "stationTest";
        Firestation firestation = new Firestation(address,1);
        when(dataLoader.getFirestations()).thenReturn(List.of(firestation));

        //when saving a firestation in FirestationService
        Optional<Firestation> resultWithExistingAddress = firestationRepositoryFromJson.findFirestationByAddress(address);

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();
        assertTrue(resultWithExistingAddress.isPresent());

    }

    @Test
    public void testfindFirestationWithWrongAddress() {

        //given an address
        final String address = "a good address";
        final String wrongAddress ="a wrong address";
        Firestation firestation = new Firestation(address,1);
        when(dataLoader.getFirestations()).thenReturn(List.of(firestation));

        //when saving a firestation in FirestationService
        Optional<Firestation> resultWithAWrongAddress = firestationRepositoryFromJson.findFirestationByAddress(wrongAddress);

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();
        assertFalse(resultWithAWrongAddress.isPresent());

    }

    @Test
    public void testgetFirestationsByGoodStationNumber() {
        //given a Station
        final int goodSation = 1;
        final String address = "a good address";
        Firestation firestation = new Firestation(address, goodSation);
        when(dataLoader.getFirestations()).thenReturn(List.of(firestation));

        //when saving a firestation in FirestationService
        List<Firestation> resultWithAGoodStation = firestationRepositoryFromJson.getFirestationsByStation(goodSation);

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();
        assertEquals(resultWithAGoodStation.size(),1);
        assertEquals(resultWithAGoodStation.getFirst(),firestation);

    }

    @Test
    public void testgetFirestationsByWrongStationNumber() {
        //given a Station
        final int goodSation = 1;
        final int wrongStation = 2;
        final String address = "a good address";
        Firestation firestation = new Firestation(address, goodSation);
        when(dataLoader.getFirestations()).thenReturn(List.of(firestation));

        //when saving a firestation in FirestationService
        List<Firestation> resultWithAGoodStation = firestationRepositoryFromJson.getFirestationsByStation(wrongStation);

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();
        assertEquals(resultWithAGoodStation.size(),0);

    }

    @Test
    public void testgetFirestations() {
        //given an address
        final String address = "stationTest";
        Firestation firestation = new Firestation(address,1);
        when(dataLoader.getFirestations()).thenReturn(List.of(firestation));

        //when saving a firestation in FirestationService
        List<Firestation> resultFirestation = firestationRepositoryFromJson.getFirestations();

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();
        assertEquals(resultFirestation.size(),1);
        assertEquals(resultFirestation.getFirst(),firestation);

    }

    @Test
    public void testaddFirestation() {
        //given an address
        final String address = "stationTest";
        Firestation firestation = new Firestation(address,1);

        //when saving a firestation in FirestationService
        firestationRepositoryFromJson.addFirestation(firestation);

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();

    }

    @Test
    public void testdeleteFirestationByAddress() {

        //given an address
        final String address = "stationTest";
        Firestation firestation = new Firestation(address,1);

        //when saving a firestation in FirestationService
        firestationRepositoryFromJson.deleteFirestationByAddress(address);

        // then the station is added to the repository
        verify(dataLoader, times(1)).getFirestations();

    }
}
