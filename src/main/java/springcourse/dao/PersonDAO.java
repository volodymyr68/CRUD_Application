package springcourse.dao;

import org.springframework.stereotype.Component;
import springcourse.models.Person;

import java.util.*;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Vova", 18,"lif4xiks@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Kristina", 19,"dwgwqge@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Tkach", 20,"xcvvxc@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Yaril", 21,"opmpgth@gmail.com"));
    }

    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(person->person.getId()==id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void update(int id,Person UpdatedPerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(UpdatedPerson.getName());
        personToBeUpdated.setAge(UpdatedPerson.getAge());
        personToBeUpdated.setEmail(UpdatedPerson.getEmail());
    }
    public void delete(int id){
        people.removeIf(p-> p.getId()==id);
    }
}
