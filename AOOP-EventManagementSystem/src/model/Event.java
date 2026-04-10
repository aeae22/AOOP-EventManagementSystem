package model;

import jakarta.persistence.*;
import java.util.List;

@Entity 
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String date;

    
}
