package com.taskknexel.taskisknexel.controller;

import com.taskknexel.taskisknexel.model.Users;
import com.taskknexel.taskisknexel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@RestController
public class UsersController {


    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> allUsers(){
        List<Users> users = userService.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name, @RequestParam("date_of_birth") String date_of_birth, @RequestParam("number") String number, @RequestParam("photo") MultipartFile photo) throws IOException {
        if (!first_name.isEmpty() && !last_name.isEmpty() && !date_of_birth.isEmpty() && !number.isEmpty() && photo.getSize()!=0) {
            Random random = new Random();
            byte[] bytes = photo.getBytes();
            String UPLOADED_FOLDER = "C:/Users/579/IdeaProjects/taskisknexel-front/images/";
            Path path = Paths.get(UPLOADED_FOLDER + random.nextInt(100) + photo.getOriginalFilename());
            Files.write(path, bytes);
            Users users = new Users();
            users.setFirst_name(first_name);
            users.setLast_name(last_name);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = null;
            try {
                date = format.parse(date_of_birth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            users.setDate_of_birth(date);
            users.setNumber(number);
            users.setPhoto(path.toString().substring(52));

            Users users1 = userService.create(users);
            return new ResponseEntity<>(users1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id){
        Users users = userService.findById(id);
        if (users == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id){
        Users users = userService.findById(id);
        if (users == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody Users users){
        Users usersedit = userService.findById(id);
        if (usersedit == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        usersedit.setFirst_name(users.getFirst_name());
        usersedit.setLast_name(users.getLast_name());
        usersedit.setDate_of_birth(users.getDate_of_birth());
        usersedit.setNumber(users.getNumber());
        userService.edit(usersedit);
        return new ResponseEntity<>(usersedit,HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteall")
    public ResponseEntity deleteAll(){
        userService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
