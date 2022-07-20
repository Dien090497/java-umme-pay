package vn.unicloud.vietqr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.unicloud.vietqr.entity.Nickname;

import java.util.List;

public interface ClientRepository extends MongoRepository<Nickname, String> {

    List<Nickname> findAll();

    Nickname findFirstByNickname(String nickname);

}
