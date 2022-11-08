package vn.unicloud.umeepay.dtos.merchant.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.entity.Merchant_;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.entity.User_;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class CmsGetMerchantMemberRequest extends BaseRequestData implements Specification<User> {

    @JsonIgnore
    private String merchantId;

    private String username;

    private UserStatus status;

    @JsonIgnore
    private Pageable pageable;


    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNoneEmpty(merchantId)) {
            predicates.add(builder.equal(root.join(User_.MERCHANT).get(Merchant_.ID), merchantId));
        }

        if (StringUtils.isNoneEmpty(username)) {
            predicates.add(builder.like(root.get(User_.USERNAME), "%" + username + "%"));
        }

        if (Objects.nonNull(status)) {
            predicates.add(builder.equal(root.get(User_.STATUS), status));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
