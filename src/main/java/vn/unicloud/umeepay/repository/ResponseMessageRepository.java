package vn.unicloud.umeepay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.unicloud.umeepay.entity.ResponseMessage;

import java.util.Optional;

@Repository
public interface ResponseMessageRepository extends JpaRepository<ResponseMessage, Long> {

    Optional<ResponseMessage> findFirstByCode(Integer code);

}
