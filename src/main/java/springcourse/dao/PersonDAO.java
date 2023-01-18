package springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import springcourse.models.Person;

import java.sql.*;
import java.util.*;
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() throws SQLException {
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
       // return people.stream().filter(person->person.getId()==id).findAny().orElse(null);
       return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},
                       new BeanPropertyRowMapper<>(Person.class))
               .stream().filter(person->person.getId()==id).findAny().orElse(null);
    }

    public void save(Person person){
        List<Integer> extraId = jdbcTemplate.queryForList("SELECT id FROM person ORDER BY id DESC LIMIT 1",
                Integer.class);
        jdbcTemplate.update("INSERT INTO Person VALUES (?,?,?,?)",extraId.get(0)+1,
                person.getName(), person.getAge(),person.getEmail());
    }
    public void update(int id,Person UpdatedPerson){
        jdbcTemplate.update("UPDATE Person SET name=?,age=?,email=? WHERE id=?",
                UpdatedPerson.getName(),UpdatedPerson.getAge(),UpdatedPerson.getEmail(),id) ;
    }
   public void delete(int id){
    jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}
