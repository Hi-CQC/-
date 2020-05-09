package com.youfan.mapper;

import com.youfan.entity.Person;

import java.util.List;

public interface PersonMapper {

    public Person queryPersonById(int id);
    public List<Person> queryPersonByName(String name);
    public void insertPerson(Person person);
    public void deletePersonById(int id);
    public void updatePersonById(Person person);

}
