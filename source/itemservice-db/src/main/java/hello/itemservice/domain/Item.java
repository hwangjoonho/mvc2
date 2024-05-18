package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    /**
     * 분석을 시작할때는 Domain 분석부터 시작한다.
     * Domain -> Repository(ServiceImpl)-> Interface 파라미터 부분을 통한 DTO -> ServiceImpl-> Service -> Controller -> 화면 front
     */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
