package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.dto.person.Home;
import com.openclassrooms.P5.dto.person.PersonInfoLastName;
import com.openclassrooms.P5.dto.person.PersonWithPhoneAgeMedicationsAllergies;
import com.openclassrooms.P5.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);

    void deletePersonById(String id);

    List<Child> childAlertByAddress(String address);

    Optional<Person> findPersonByid(String id);

    void updatePerson(Person updatedPerson);

    List<PersonWithPhoneAgeMedicationsAllergies> personLivingAtAddress(String address);

    List<Home> homesByStation(List<Integer> stations);

    List<PersonInfoLastName> personsInfoByLastName(String lastname);

    List<String> phoneAlertByFirestationID(Integer firestation);
}
