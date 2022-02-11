package com.linda.todo.service;

import com.linda.todo.repository.EventRepository;
import com.linda.todo.model.EventEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EventService {
    @Resource
    EventRepository eventRepository;

    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }


}
