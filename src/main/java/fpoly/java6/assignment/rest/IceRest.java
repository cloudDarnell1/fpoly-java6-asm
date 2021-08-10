package fpoly.java6.assignment.rest;

import fpoly.java6.assignment.service.IceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ices")
@CrossOrigin(origins = "${server.cors}")
public class IceRest {

    @Autowired
    private IceService iceService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(this.iceService.findAll());
    }
}
