package de.toomuchcoffee.httsb.bidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "ENTITY_A")
public class EntityA {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "a", fetch = EAGER)
    private Set<EntityB> bs;

}
