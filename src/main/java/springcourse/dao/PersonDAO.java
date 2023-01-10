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
        people.add(new Person(++PEOPLE_COUNT,"Vova"));
        people.add(new Person(++PEOPLE_COUNT,"Kristina"));
        people.add(new Person(++PEOPLE_COUNT,"Tkach"));
        people.add(new Person(++PEOPLE_COUNT,"Yarik"));
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
}
