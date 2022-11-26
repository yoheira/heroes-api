/**
 * 
 */
package itli.diplomado.heroes.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * @author enunezt
 *
 */
@Entity (name="HEROES" )
public class Heroe implements Serializable{
    
    @Id
    @Column(name="HEROE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //el nombre no necesariamente es igual
    
    @Column(name="NAME")
    private String name;   
    
    @ManyToMany() //cascade = { CascadeType.ALL }
    @JoinTable(name = "REL_HEROE_PODERES",
            joinColumns = { @JoinColumn(name = "HEROE_ID", foreignKey = @ForeignKey(name = "FK_REL_HEPO_HEROE"))},
            inverseJoinColumns = { @JoinColumn(name = "PODER_ID", foreignKey = @ForeignKey(name = "FK_REL_HEPO_PODER")) })
    private Set<Poder> poderes = new HashSet<Poder>(); 
        
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ID", foreignKey = @ForeignKey(name = "FK_HEROE_TIPO"))
    private Tipo tipo;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Set<Poder> getPoderes() {
        return poderes;
    }


    public void setPoderes(Set<Poder> poderes) {
        this.poderes = poderes;
    }


    public Tipo getTipo() {
        return tipo;
    }


    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    
    
  
}
