package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);

    @Query(value="select * from lessons where delivered_on between :date1 and :date2", nativeQuery=true)
    Iterable<Lesson> findByDateBetween(@Param("date1") String date1,
                                   @Param("date2") String date2);
}
