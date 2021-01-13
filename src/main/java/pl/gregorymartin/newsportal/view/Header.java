package pl.gregorymartin.newsportal.view;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "headers")
class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long postId;

    private int sequence;
}
