package shop.models;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "custorder")
@Data
public class CustOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "date")
    private Date orderDate;

    @Column(name = "amount")
    private int amount;

//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable=false)
//    private Customer customer;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable=false)
//    private Customer product;

}
