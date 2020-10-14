package com.example.backtoken.api;

import com.example.backtoken.model.Invitetk;
import com.example.backtoken.repository.InvitetkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("invites")
public class InviteAPI {
    private final InvitetkRepository invitetkDAO;

    @Autowired
    public InviteAPI(InvitetkRepository invitetkDAO) {
        this.invitetkDAO = invitetkDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return  new ResponseEntity<>(invitetkDAO.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/us_ev/{id_event}/{id_user}")
    public ResponseEntity<?> getEventById(@PathVariable("id_event") long id_event,
                                          @PathVariable("id_user") long id_user){
        Invitetk invite = invitetkDAO.findById_eventAndId_user(id_event, id_user);
        if(invite == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(invite, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Invitetk invite){
        return new ResponseEntity<>(invitetkDAO.save(invite),HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Invitetk invite){
        invitetkDAO.delete(invite);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Invitetk invite){
        invitetkDAO.save(invite);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    
}
