package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity     // JPA 객체 인정
public class Item {

    /**
     * 분석을 시작할때는 Domain 분석부터 시작한다.
     * Domain -> Repository(ServiceImpl)-> Interface 파라미터 부분을 통한 DTO -> ServiceImpl-> Service -> Controller -> 화면 front
     */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 설정 및 PK 생성 값을 데이터베이스에서 생성하는 IDENTITY 방식을 사용
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {         // public 또는 protected 로 설정된 기본생성자가 있어야 jpa 프레임워크에서 proxy 접근 가능 
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
