package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    public LessonController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional <Lesson> read(@PathVariable long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Lesson update(@PathVariable long id, @RequestBody Lesson lesson){
        Optional<Lesson> lesArray = this.repository.findById(id);
        Lesson updatedLes = lesArray.get();
        updatedLes.setTitle(lesson.getTitle());
        updatedLes.setDeliveredOn(lesson.getDeliveredOn());
        this.repository.save(updatedLes);
        return updatedLes;

    }

    @GetMapping("/find/{title}")
    public Lesson read(@PathVariable String title){
        return this.repository.findByTitle(title);
    }

    @GetMapping("/between")
    public Iterable<Lesson> between(@RequestParam String date1, @RequestParam String date2){
        /*List<Lesson> lessonList = new ArrayList<>();
        (this.repository.findAll()).forEach(lesson -> {
            if (lesson.getDeliveredOn().before(d1) && lesson.getDeliveredOn().after(d2)){
                lessonList.add(lesson);
            }
        } );*/

        return this.repository.findByDateBetween(date1, date2);
    }


}