package com.example.backtoken.api;

import com.example.backtoken.model.Usertk;
import com.example.backtoken.repository.UsertkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserAPI {
    private final UsertkRepository usertkDAO;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserAPI(UsertkRepository usertkDAO) {
        this.usertkDAO = usertkDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return  new ResponseEntity<>(usertkDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{us_id}")
    public ResponseEntity<?> getEventById(@PathVariable("us_id") long us_id){
        Optional<Usertk> _user = usertkDAO.findById(us_id);
        if(_user == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @GetMapping(path = "/name/{us_nome}")
    public ResponseEntity<?> getEventByName(@PathVariable("us_nome") String us_nome){
        List<Usertk> _user = usertkDAO.findByUs_nomeIgnoreCaseContaining(us_nome);
        if(_user == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @GetMapping(path = "/login/{us_login}")
    public ResponseEntity<?> getEventBylogin(@PathVariable("us_login") String us_login){
        Usertk _user = usertkDAO.findByUs_login(us_login);
        if(_user == null)
            return new ResponseEntity<>("adasd", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usertk _user){
        _user.setUs_senha(passwordEncoder.encode(_user.getUs_senha()));
        return new ResponseEntity<>(usertkDAO.save(_user),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Usertk _user){
        usertkDAO.delete(_user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Usertk _user){
        usertkDAO.save(_user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
