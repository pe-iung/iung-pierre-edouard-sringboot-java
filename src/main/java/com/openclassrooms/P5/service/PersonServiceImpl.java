package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.dto.person.PersonInfoLastName;
import com.openclassrooms.P5.dto.person.PersonListAndCountByStationNumber;
import com.openclassrooms.P5.dto.person.PersonWithPhoneAgeMedicationsAllergies;
import com.openclassrooms.P5.exceptions.ConflictException;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FirestationRepositoryFromJson firestationRepositoryFromJson;

    /**
     * save a person and prevent from duplicating existing personId
     * the personId is "firstname+-lastname"
     * @param person
     * @return person
     */
    @Override
    public Person savePerson(Person person) {
        if (findPersonByid(person.getId()).isPresent()) {
            throw new ConflictException("Person already exist for firstname-lastname =" + person.getId());
        }
        personRepository.addPerson(person);
        return person;
    }

    /**
     * delete a person with existing personId
     * elese warn the user of non existing personID
     * @param person
     * @return void
     */
    @Override
    public void deletePersonById(String id) {
        personRepository.deletePersonById(id);
    }

    /**
     * send a child Alert for a given address.
     * Each child in the list returned should also have the list of each of his family member.
     * If there is not child, the list of child could be empty
     * @param address
     * @return a List of Child (person with age <= 18) living at the given adress
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

    /**
     * find a person by string id = firstname-lastname
     * @param id
     * @ Optional (Person)
     */
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

    /**
     * should return the email addresses of all the residents of the city.
     * @param city
     * @return list email addresses of all the residents of the city.
     */
    public List<String> citizenEmailListByCity(String city) {

        return personRepository.getPersonsByCity(city)
                .stream()
                .map(Person::getEmail)
                .toList();
    }

    /**
     * Should return the list of residents living at the given address,
     * as well as the number of the fire station serving that address.
     * The list must include the name, phone number, age, and
     * medical history (medications, dosage, and allergies) of each person.
     * @param address
     * @return list PersonWithPhoneAgeMedicationsAllergies living at given address
     */
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

    /**
     * Should return a list of all households served by the fire station.
     * The list must group people by address. It should also include the name,
     * phone number, and age of the residents, along with their medical history
     * (medications, dosage, and allergies) listed next to each name.
     * @param stations
     * @return list of all households served by the fire station
     */
    @Override
    public Map<String, List<PersonWithPhoneAgeMedicationsAllergies>> homesByStation(List<Integer> stations) {


        return firestationRepositoryFromJson.getFirestations()
                .stream()
                .filter(s -> stations.contains(s.getStation()))
                .distinct()
                .map(station -> station.getAddress())
                .flatMap(address -> personWithMedicalRecord(address))
                .collect(groupingBy(p -> p.person().getAddress(), mapping(p -> new PersonWithPhoneAgeMedicationsAllergies(p), toList())));


    }

    private Stream<PersonWithMedicalRecord> personWithMedicalRecord(String address) {
        return personRepository.getPersonsByAddress(address)
                .stream()
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()));
    }

    /**
     * Should return the name, address, age,
     * email address, and medical history (medications, dosage, and allergies) of each resident.
     * If multiple people have the same name, they must all be included.
     * @param lastname
     * @return list of PersonInfoLastName
     */
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

    /**
     *  Should return a list of phone numbers of residents served by the fire station.
     *  It will be used to send emergency text messages to specific households.
     * @param firestation
     * @return list of phoneNumber
     */
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

    /**
     * should return a list of people covered by the corresponding fire station.
     * For example, if the station number = 1, it should return the residents covered by station number 1.
     * The list must include the following specific information: first name, last name, address, and phone number.
     * Additionally, it must provide a count of the number of adults and the number of children
     * (any individual aged 18 or younger) in the covered area.
     * @param stationNumber
     * @return PersonListAndCountByStationNumber
     */
    @Override
    public PersonListAndCountByStationNumber getPersonsListAndCountByStationNumber(int stationNumber) {

        var persons = firestationRepositoryFromJson.getFirestationsByStation(stationNumber)
                .stream()
                .map(s -> s.getAddress())
                .distinct()
                .flatMap(address -> personRepository.getPersonsByAddress(address).stream())
                .map(p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()))
                .toList();

        return new PersonListAndCountByStationNumber(persons);

    }
}
