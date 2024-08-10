package com.project.demo.rest.specie;

import com.project.demo.logic.entity.specie.Specie;
import com.project.demo.logic.entity.specie.SpecieDTO;
import com.project.demo.logic.entity.specie.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/specie")
public class specieRestController {


    @Autowired
    private SpecieRepository specieRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'USER')")
    public List<Specie> getAllSpecie() {
        return (List<Specie>) specieRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'USER')")
    public Specie addSpecie(@RequestBody Specie specie) {
        return specieRepository.save(specie);
    }

    @GetMapping("/{id}")
    public Specie getSpecieById(@PathVariable Long id) {
        return specieRepository.findById(id).orElseThrow(() -> new RuntimeException("Specie not found"));
    }
    @PostMapping("/addSpecie")
    public Specie addSpecie(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) {
        String directory = "D:\\Tarea_2\\TaskieTamer_Front\\src\\assets\\taskies";
        Path path = Paths.get(directory);

        try {
                        if (!Files.exists(path)) {
                Files.createDirectories(path);
            }


            String fileName = file.getOriginalFilename();
            Path filePath = path.resolve(fileName);
            Files.write(filePath, file.getBytes());

            Specie specie = new Specie();
            specie.setName(name);
            specie.setDescription(description);
            specie.setSprite("../../../assets/taskies/" + fileName);

            return specieRepository.save(specie);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file or specie", e);
        }
    }

    @PutMapping("/{id}")
    public Specie updateSpecie(@PathVariable Long id, @RequestBody Specie specie) {
        return specieRepository.findById(id)
                .map(existingSpecie -> {
                    existingSpecie.setName(specie.getName());
                    existingSpecie.setDescription(specie.getDescription());
                    existingSpecie.setSprite(specie.getSprite());
                    return specieRepository.save(existingSpecie);
                })
                .orElseGet(() -> {
                    specie.setId(id);
                    return specieRepository.save(specie);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteSpecie(@PathVariable Long id) {
        specieRepository.deleteById(id);
    }
}
