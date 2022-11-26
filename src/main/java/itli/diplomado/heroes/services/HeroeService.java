package itli.diplomado.heroes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itli.diplomado.heroes.models.Heroe;
import itli.diplomado.heroes.models.Poder;
import itli.diplomado.heroes.repository.HeroeRepository;
import itli.diplomado.heroes.repository.PoderRepository;

@Service
@Transactional(noRollbackFor = Exception.class)
public class HeroeService {

    @Autowired
    HeroeRepository heroeRepository;
    
    @Autowired
    PoderRepository poderRepository;

    
    /**
     * Consulta todos los heroes
     * @return
     */
    public List<Heroe> getList(){  
        
        List<Heroe> herolesList= heroeRepository.findAll();        
        herolesList.stream().forEach(heroe -> heroe.getPoderes().size());
        
        return herolesList;                
    }
    
    /**
     * Guardar un Heroe
     * @param heroe
     * @return
     */
    public Heroe save(Heroe heroe) {
        
        heroeRepository.save(heroe);
       /* Heroe newHeroe = new Heroe();
        newHeroe.setName(heroe.getName());
        newHeroe.setTipo(heroe.getTipo());
        
        heroe.getPoderes().stream().forEach(poder -> 
                            newHeroe.getPoderes().add( poderRepository.getReferenceById(poder.getId()))
                        );        
         
        heroeRepository.save(heroe);*/
         //heroe = heroeRepository.findById(heroe.getId()).get();
         //System.out.println(heroe.getTipo());
         
         return heroe;
    }
    
    /**
     * Borrar un heroe
     * @param id
     */
    public void delete(Integer id) {
        
        heroeRepository.deleteById(id); 
    }
    
    /**
     * Guardar un Heroe
     * @param heroe
     * @return
     */
    public Heroe update(Heroe heroe) {
        return heroeRepository.save(heroe);
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public boolean existsById(int id){
        return heroeRepository.existsById(id);
    }
    
    public Heroe findById(int id){    
        Optional<Heroe> e=heroeRepository.findById(id);
        
        e.ifPresent((value)->{
            System.out.println("Heore Poderes : " + value.getPoderes().size());
        });
               
        // heroeRepository.findById(id).map(Heroe::getTipos);
        return e.get();
    }
    
}
