package dev.serrodcal;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "persons")
@Cacheable
public class PersonEntity extends PanacheEntityBase {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "personsSequence", schema = "public", sequenceName = "persons_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personsSequence")
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public Integer age;

//    @Column
//    public String email;

    //public static PersonEntity of(String name, Integer age, String email) {
    public static PersonEntity of(String name, Integer age) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.name = name;
        personEntity.age = age;
        //personEntity.email = email;

        return personEntity;
    }

    public PersonResponse toResponse() {
        return new PersonResponse(this.id, this.name, this.age);
        //return new PersonResponse(this.id, this.name, this.age, this.email);
    }

}
