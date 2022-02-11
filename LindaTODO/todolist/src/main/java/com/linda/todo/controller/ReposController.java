package com.linda.todo.controller;

import com.linda.todo.model.EventEntity;
import com.linda.todo.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ReposController {

    @Resource
    private EventRepository eventRepository;


    @GetMapping("/events")
    public ResponseEntity<List<EventEntity>> getAllTodos(@RequestParam(required = false) String desc) {
        try {
            List<EventEntity> events = new ArrayList<>();
            //輸入關鍵字
            if (desc == null) {
                //前端沒輸入按搜尋->找全部
                eventRepository.findAll().forEach(events::add);
            }else {
                //前端有輸入->找關鍵字全部
                eventRepository.findByDescContaining(desc).forEach(events::add);
            }
            //DB沒有資料
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventEntity> getTodoById(@PathVariable("id") long id) {
        Optional<EventEntity> eventData = eventRepository.findById(id);

        if (eventData.isPresent()) {
            return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events")
    public ResponseEntity<EventEntity> insertTodo(@RequestBody EventEntity event) {
        try {
            EventEntity eventEntity = eventRepository.selectByDesc(event.getDesc());
            if(eventEntity == null) {
                EventEntity _event = eventRepository
                        .save(new EventEntity(event.getDesc()));
                return new ResponseEntity<>(_event, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/events/{id}")
    public ResponseEntity<EventEntity> updateTodo(@PathVariable("id") long id, @RequestBody EventEntity event) {
        Optional<EventEntity> eventData = eventRepository.findById(id);

        if (eventData.isPresent()) {
            //若用id查DB有此event物件，就update
            EventEntity _eventData = eventData.get();
            _eventData.setDesc(event.getDesc());
            return new ResponseEntity<>(eventRepository.save(_eventData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") long id) {
        try {
            eventRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/events")
    public ResponseEntity<HttpStatus> deleteAllEvents() {
        try {
            eventRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}


