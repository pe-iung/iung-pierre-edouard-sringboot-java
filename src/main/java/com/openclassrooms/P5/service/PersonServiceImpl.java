package com.openclassrooms.P5.service;

import com.openclassrooms.P5.dto.person.Child;
import com.openclassrooms.P5.exceptions.NotFoundException;
import com.openclassrooms.P5.model.Person;
import com.openclassrooms.P5.model.PersonWithMedicalRecord;
import com.openclassrooms.P5.repository.MedicalRecordRepository;
import com.openclassrooms.P5.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

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


        List<PersonWithMedicalRecord> personListLivingAtAddress =  personRepository.getPersonsByAddress(address)
                .stream()
                .map( p -> new PersonWithMedicalRecord(p, medicalRecordRepository.findMedicalRecordById(p.getId()).orElseThrow()))
                .toList();


        return personListLivingAtAddress
                .stream()
                .filter(personWithMedicalRecord -> personWithMedicalRecord.medicalRecord().isMinor())
                .map(m ->  new Child(m, personListLivingAtAddress)  )
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
        personRepository.getPersons().set(index,updatedPerson);
    }





}
