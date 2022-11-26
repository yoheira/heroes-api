package itli.diplomado.heroes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itli.diplomado.heroes.dtos.Mensaje;
import itli.diplomado.heroes.models.Heroe;
import itli.diplomado.heroes.services.HeroeService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroeControlller {
    
    @Autowired
    HeroeService heroeService;    
    
    
    @GetMapping("/heroes")
    public ResponseEntity<List<Heroe>> getList(){        
        List<Heroe> heroesList=heroeService.getList();
        return new ResponseEntity<List<Heroe>>(heroesList,HttpStatus.OK);
    }
    
    @GetMapping("/heroes/{id}")
    public ResponseEntity<?> getHeroe(@PathVariable("id")int id){        
           
       Heroe heroe= heroeService.findById(id);
        
        if(heroe==null)
            return new ResponseEntity(new Mensaje("Error en el servidor"), HttpStatus.NOT_FOUND);
    
        return new ResponseEntity(heroe, HttpStatus.OK);
    }
    
    
    @PostMapping("/heroes")
    public ResponseEntity<?> create(@RequestBody Heroe heroe){        
       
        if(heroe.getName() ==null || "".equals(heroe.getName())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."),HttpStatus.BAD_REQUEST);
        }
        
        try {
            Heroe heroeSaved=heroeService.save(heroe);
            return new ResponseEntity<Heroe>(heroeSaved,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Mensaje>(new Mensaje("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PutMapping("/heroes/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody Heroe heroe){        
       
        if(heroe==null) {
            return new ResponseEntity<Mensaje>(new Mensaje("Objeto Heroe es obligatorio."),HttpStatus.BAD_REQUEST);
        }
        
        if(id<=0) {
            return new ResponseEntity<Mensaje>(new Mensaje("El ID es obligatorio."),HttpStatus.BAD_REQUEST);
        }
        
        if(heroe.getName() ==null || "".equals(heroe.getName())) {
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre es obligatorio."),HttpStatus.BAD_REQUEST);
        }
        
        try {
            
            heroe.setId(id);
            Heroe heroeSaved=heroeService.update(heroe);
            return new ResponseEntity<Heroe>(heroeSaved,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Mensaje>(new Mensaje("Error en el servidor"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){       
       
        if(!heroeService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("Error en el servidor"), HttpStatus.NOT_FOUND);
        heroeService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Heroe eliminado"), HttpStatus.OK);
    }
    

}
