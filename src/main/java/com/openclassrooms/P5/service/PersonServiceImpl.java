package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.dto.person.Home;
import com.openclassrooms.P5.dto.person.PersonInfoLastName;
import com.openclassrooms.P5.dto.person.PersonWithPhoneAgeMedicationsAllergies;
import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.model.PersonWithMedicalRecord;
import com.openclassrooms.P5.repository.FirestationRepositoryFromJson;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import com.openclassrooms.P5.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    /*
    http://localhost:8080/childAlert?address=<address>
    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    La liste doit comprendre le prénom et le nom de famille de chaque enfant,
    son âge et une liste des autres membres du foyer.
    S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
     */
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
    public List<Home> homesByStation(List<Integer> stations) {

        //for each stations, find the matching firestions address
//        List<String> fireStationAdresses = stations
//                .stream()
//                .map(s-> firestationRepositoryFromJson.getFirestationsByStation(s)
//                        .stream()
//                        .map(Firestation::getAddress)
//                        .toString())
//                .toList();
//
//        //for each firestation address find the personLivingAtAddress(String address)
//        return fireStationAdresses
//                .stream()
//                .map(s-> new Home(s, personLivingAtAddress(s)))
//                .toList();

        //todo : check if possible to use stream here instead of for loop
        List<Home> homes = Lists.newArrayList();
        for (int station_id : stations) {
            List<String> addresses = firestationRepositoryFromJson.getFirestationsByStation(station_id)
                    .stream()
                    .map(s -> s.getAddress())
                    .toList();
            for (String address : addresses) {
                homes.add(new Home(address, personLivingAtAddress(address)));
            }
        }

        return homes;
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
}
