package ru.otus.crm.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
@NamedEntityGraph(
        name = "client-eg",
        attributeNodes = {
                @NamedAttributeNode("address"),
                @NamedAttributeNode("phones")
        }
)
public class Client implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("id")
    private List<Phone> phones = new ArrayList<>();

    public void addPhones(List<Phone> phones) {
        this.phones = phones;
        phones.forEach(phone -> phone.setClient(this));
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.address = address;
        addPhones(phones);
    }

    @Override
    public Client clone() {
        var clonedClient = new Client(this.id, this.name, this.address, this.phones);
        clonedClient.addPhones(this.phones);
        return clonedClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
