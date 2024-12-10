package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.person.*;
import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.model.PersonWithMedicalRecord;
import com.openclassrooms.P5.repository.FirestationRepositoryFromJson;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import com.openclassrooms.P5.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FirestationRepositoryFromJson firestationRepositoryFromJson;

    @Override
    public Person savePerson(Person person) {
        personRepository.addPerson(person);
        return person;
    }

    @Override
    public void deletePersonById(String id) {
        personRepository.deletePersonById(id);
    }


    @Override
    public List<Child> childAlertByAddress(String address) {


        List<PersonWithMedicalRecord> personListLivingAtAddress = personRepository.getPersonsByAddress(address)
                .stream()
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()))
                .toList();


        return personListLivingAtAddress
                .stream()
                .filter(personWithMedicalRecord -> personWithMedicalRecord.medicalRecord().isMinor())
                .map(m -> new Child(m, personListLivingAtAddress))
                .toList();
    }

    @Override
    public Optional<Person> findPersonByid(String id) {

        return personRepository.findPersonById(id);
    }

    @Override
    public void updatePerson(Person updatedPerson) {
        String updatedPersonId = updatedPerson.getId();
        Person existingPerson = findPersonByid(updatedPersonId)
                .orElseThrow(() -> new NotFoundException("Person not found with id : " + updatedPersonId));
        int index = personRepository.getPersons().indexOf(existingPerson);
        personRepository.getPersons().set(index, updatedPerson);
    }


    public List<String> citizenEmailListByCity(String city) {

        return personRepository.getPersonsByCity(city)
                .stream()
                .map(Person::getEmail)
                .toList();
    }

    @Override
    public List<PersonWithPhoneAgeMedicationsAllergies> personLivingAtAddress(String address) {

        List<PersonWithMedicalRecord> personListLivingAtAddress = personRepository.getPersonsByAddress(address)
                .stream()
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()))
                .toList();

        return personListLivingAtAddress
                .stream()
                .map(PersonWithPhoneAgeMedicationsAllergies::new)
                .toList();
    }

    @Override
    public Map<String, List<PersonWithPhoneAgeMedicationsAllergies>> homesByStation(List<Integer> stations) {



        return firestationRepositoryFromJson.getFirestations()
                .stream()
                .filter(s -> stations.contains(s.getStation()))
                .distinct()
                .map(station -> station.getAddress())
                .flatMap( address ->  personWithMedicalRecord(address))
                .collect(groupingBy(p -> p.person().getAddress(), mapping(p -> new PersonWithPhoneAgeMedicationsAllergies(p), toList())));



    }

    private Stream<PersonWithMedicalRecord> personWithMedicalRecord(String address){
        return personRepository.getPersonsByAddress(address)
                .stream()
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()));
    }

    @Override
    public List<PersonInfoLastName> personsInfoByLastName(String lastname) {

        List<PersonWithMedicalRecord> personListByLastName = personRepository.getPersons()
                .stream()
                .filter(person -> person.getLastName().equals(lastname))
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()))
                .toList();

        return personListByLastName
                .stream()
                .map(p -> new PersonInfoLastName(
                        p.person().getFirstName(),
                        p.person().getLastName(),
                        p.person().getAddress(),
                        p.medicalRecord().getAge(),
                        p.person().getEmail(),
                        p.medicalRecord().getMedications(),
                        p.medicalRecord().getAllergies()
                ))
                .toList();
    }

    @Override
    public List<String> phoneAlertByFirestationID(Integer firestation) {
        //todo : check if possible to use stream here instead of for loop
        List<String> phoneAlertList = Lists.newArrayList();

        List<String> addresses = firestationRepositoryFromJson.getFirestationsByStation(firestation)
                .stream()
                .map(s -> s.getAddress())
                .toList();

        for (String address : addresses) {
            List<Person> personList = personRepository.getPersonsByAddress(address)
                    .stream()
                    .toList();

            for (Person p : personList) {
                phoneAlertList.add(p.getPhone());
            }

        }
        return phoneAlertList;
    }

    @Override
    public PersonListAndCountByStationNumber getPersonsListAndCountByStationNumber(int stationNumber) {

        var persons = firestationRepositoryFromJson.getFirestationsByStation(stationNumber)
                .stream()
                .map(s -> s.getAddress())
                .distinct()
                .flatMap(address -> personRepository.getPersonsByAddress(address).stream() )
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()))
                .toList();

        return new PersonListAndCountByStationNumber(persons);
        
    }
}
