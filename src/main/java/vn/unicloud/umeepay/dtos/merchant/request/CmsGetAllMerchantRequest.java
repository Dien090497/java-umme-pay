package vn.unicloud.umeepay.dtos.merchant.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.entity.*;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.MerchantStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class CmsGetAllMerchantRequest extends BaseRequestData implements Specification<Merchant> {

    private String name;
    private String username;
    private Date requestDateFrom;
    private Date requestDateTo;
    private BusinessType businessType;
    private MerchantStatus status;

    @JsonIgnore
    private Pageable pageable;

    @Override
    public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> fPredicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(name)) {
            fPredicates.add(builder.like(root.get(Merchant_.NAME), "%" + name + "%"));
        }

        if (StringUtils.isNotEmpty(username)) {
            fPredicates.add(builder.like(root.join(Merchant_.USER).get(User_.USERNAME), "%" + username + "%"));
        }

        if (Objects.nonNull(businessType)) {
            fPredicates.add(builder.equal(root.join(Merchant_.PROFILE).get(Profile_.BUSINESS_TYPE), businessType));
        }

        if (Objects.nonNull(requestDateFrom)) {
            fPredicates.add(builder.greaterThanOrEqualTo(builder.function("date", Date.class, root.get(Merchant_.REQUEST_AT)), requestDateFrom));
        }

        if (Objects.nonNull(requestDateTo)) {
            fPredicates.add(builder.lessThanOrEqualTo(builder.function("date", Date.class, root.get(Merchant_.REQUEST_AT)), requestDateTo));
        }

        if (Objects.nonNull(status)) {
            fPredicates.add(builder.equal(root.get(Merchant_.STATUS), status));
        }


        return builder.and(fPredicates.toArray(new Predicate[fPredicates.size()]));
    }
}
