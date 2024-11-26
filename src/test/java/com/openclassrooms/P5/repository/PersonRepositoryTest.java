package com.openclassrooms.P5.repository;
import com.openclassrooms.P5.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Mock
    DataLoader dataloader;

    @InjectMocks
    PersonRepositoryFromJson personRepositoryFromJson;

    @Test
    public void testFindPersonByAddress() {
        //given an Address
        String address = "12 rue de la plage, Paris";

        //when we find a person by address
        personRepositoryFromJson.findPersonByAddress(address);

        //then the dataloader is called
        verify(dataloader, times(1)).getPersons();

    }

    @Test
    public void testGetPersonByFirstnameAndLastname() {
        //given a firstName and lastName
        String firstName = "bob";
        String lastName = "eponge";


        //when we find a person by address
        personRepositoryFromJson.getPersonByFirstnameAndLastname(firstName, lastName);

        //then the dataloader is called
        verify(dataloader, times(1)).getPersons();

    }

    @Test
    public void testGetPersons(){

        //given an existing dataloader


        //when we call getPersons from the repository
        personRepositoryFromJson.getPersons();

        //then the dataloader is called
        verify(dataloader, times(1)).getPersons();

    }

    @Test
    public void testAddPerson(){
        //given a Person
        Person personToTest = new Person(
                "Buzz",
                "eclair",
                "infinity road NYC",
                "New York",
                "99751",
                "30123456",
                "buzz@eclair.com"
        );

        // when we add a person from repository
        personRepositoryFromJson.addPerson(personToTest);

        //then the dataloader is called with proper arguments
        verify(dataloader, times(1)).addPerson(personToTest);

    }

    @Test
    public void testDeletePersonById(){

        //given an id
        String id = "buzz-leclair";

        // when we add a person from repository
        personRepositoryFromJson.deletePersonById(id);

        //then the dataloader is called with proper arguments
        verify(dataloader, times(1)).deletePersonById(id);


    }
}
