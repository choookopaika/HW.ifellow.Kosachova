package ifellow.schAT.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ifellow.schAT.dto.RegistrationRequestDto;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PostController {

    Map<String,String> users = new HashMap<>();
    List<UUID> uuids = new ArrayList<>();


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequestDto requestDto) {
        users.put(requestDto.username(), requestDto.password());
        return ResponseEntity.ok().body("success register");
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody RegistrationRequestDto requestDto) {
        if (!users.containsKey(requestDto.username())){
            return ResponseEntity.status(401).body("not found");
        } else if (!users.get(requestDto.username()).contains(requestDto.password())){
            return ResponseEntity.status(401).body("not right pass");
        }
        UUID uuid = UUID.randomUUID();

        uuids.add(uuid);

        return ResponseEntity.ok().body("token : " + uuid);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader(value = "Authorization") UUID token) {
        if(!uuids.contains(token)) {
            return ResponseEntity.status(401).body("not found");
        }
        uuids.remove(token);
        return ResponseEntity.ok().body("success logout");
    }
}