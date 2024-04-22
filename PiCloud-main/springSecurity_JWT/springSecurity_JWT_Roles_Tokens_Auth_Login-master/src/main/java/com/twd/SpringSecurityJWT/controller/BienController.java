package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.entity.Bien;
import com.twd.SpringSecurityJWT.service.BienService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("BienController")
public class BienController {

    BienService bienService;
    @GetMapping("/Get-All-Bien")
    public List<Bien> getAllBien(){return bienService.getAllBiens();}
    @PostMapping("/add-Bien")
    public Bien addBien(@RequestBody Bien bien){
        bien.setDateAjout(LocalDate.now());
        return bienService.addBien(bien);}
    @PutMapping("/update-Bien")
    public Bien uppdateBien(@RequestBody Bien bien){return  bienService.updateBien(bien);}
    @DeleteMapping("/delete-Bien/{idBien}")
    public void deleteBien(@PathVariable("idBien") int idBien ){bienService.removeBienById(idBien);}

    @GetMapping("/get-all-bien")
    public ResponseEntity<List<Bien>> getAllBiens() {
        List<Bien> biens = bienService.getAllBiens();
        return ResponseEntity.ok(biens);
    }
    @GetMapping("/order-asc")
    public ResponseEntity<List<Bien>> orderBiensAsc() {
        List<Bien> orderedBiens = bienService.orderBiensAsc();
        return ResponseEntity.ok(orderedBiens);
    }

    @GetMapping("/order-desc")
    public ResponseEntity<List<Bien>> orderBiensDesc() {
        List<Bien> orderedBiens = bienService.orderBiensDesc();
        return ResponseEntity.ok(orderedBiens);
    }

    @GetMapping("/search-bien")
    public ResponseEntity<List<Bien>> searchBiensByName(@RequestParam String name) {
        List<Bien> biens = bienService.searchBiensByName(name);
        return ResponseEntity.ok(biens);
    }


}
