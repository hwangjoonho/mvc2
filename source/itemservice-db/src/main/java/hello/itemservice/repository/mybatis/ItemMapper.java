package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper     //Mapper가 위치한 똑같은 디렉토리 경로에 xml 파일을 만들어준다. 파일 이름도 같아야한다.
public interface ItemMapper {

    void save(Item item);
                        
    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam); // param 2개 이상일 경우 @Param 필수

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond itemSearch);
}
