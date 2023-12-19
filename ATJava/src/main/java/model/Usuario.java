package model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String senha;
}
