package com.example.backtoken.api;


import com.example.backtoken.model.Eventtk;
import com.example.backtoken.repository.EventtkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("events")
public class EventAPI {

    private final EventtkRepository eventtkDAO;

    @Autowired
    public EventAPI(EventtkRepository eventtkDAO) {
        this.eventtkDAO = eventtkDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return  new ResponseEntity<>(eventtkDAO.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/id/{ev_id}")
    public ResponseEntity<?> getEventById(@PathVariable("ev_id") long ev_id){
        Optional<Eventtk> event = eventtkDAO.findById(ev_id);
        if(event == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping(path="/user/{us_login}")
    public ResponseEntity<?> getEventByUs_login(@PathVariable("us_login") String us_login){
        List<Eventtk> events = eventtkDAO.findAllByEv_us_login(us_login);
        if(events == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping(path = "/desc/{ev_desc}")
    public ResponseEntity<?> getEventById(@PathVariable("ev_desc") String ev_desc){
        List<Eventtk> events = eventtkDAO.findByEv_descIgnoreCaseContaining(ev_desc);
        if(events == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Eventtk event){
        return new ResponseEntity<>(eventtkDAO.save(event),HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Eventtk event){
        eventtkDAO.delete(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Eventtk event){
        eventtkDAO.save(event);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
