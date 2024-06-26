package tech.loga.dossier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import tech.loga.automobile.Automobile;
import tech.loga.client.Client;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dossier")
public class Dossier implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", unique = true, length = 24)
    private String reference;

    @Column(name = "is_archived")
    private Boolean archived;

    @Column(name = "open_at")
    private Date openAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @ManyToOne(targetEntity = Client.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @OneToOne(targetEntity = Automobile.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "automobile", referencedColumnName = "id")
    private Automobile automobile;

    public Dossier(String reference,
                   Client client,
                   Automobile automobile) {
        this.reference = reference;
        this.client = client;
        this.automobile = automobile;
    }
}
