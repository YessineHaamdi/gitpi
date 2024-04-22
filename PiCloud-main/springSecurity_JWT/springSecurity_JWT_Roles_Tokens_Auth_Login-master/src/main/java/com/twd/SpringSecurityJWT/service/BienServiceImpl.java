package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Bien;
import com.twd.SpringSecurityJWT.repository.BienRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BienServiceImpl implements BienService {

   BienRepository bienRepository;
    @Override
    public Bien addBien(Bien bien) {
        return bienRepository.save(bien);
    }

    @Override
    public Bien updateBien(Bien bien) {
        return bienRepository.save(bien);
    }

    @Override
    public Bien getBien(int idBien) {
        return bienRepository.findById(idBien).orElse(null);
    }

    @Override
    public void removeBienById(int idBien) {
        bienRepository.deleteById(idBien);
    }

    @Override
    public List<Bien> getAllBiens() {
        return bienRepository.findAll();
    }
    @Override
    public List<Bien> orderBiensAsc() {
        return bienRepository.findAll()
                .stream()
                .sorted((b1, b2) -> Float.compare(b1.getPrix(), b2.getPrix()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bien> orderBiensDesc() {
        return bienRepository.findAll()
                .stream()
                .sorted((b1, b2) -> Float.compare(b2.getPrix(), b1.getPrix()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bien> searchBiensByName(String name) {
        List<Bien> allBiens = bienRepository.findAll();
        List<Bien> filteredBiens = new ArrayList<>();

        for (Bien bien : allBiens) {
            if (bien.getNom().toLowerCase().contains(name.toLowerCase())) {
                filteredBiens.add(bien);
            }
        }

        return filteredBiens;
    }




}
