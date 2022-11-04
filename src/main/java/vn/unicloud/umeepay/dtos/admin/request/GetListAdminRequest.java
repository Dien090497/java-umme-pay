package vn.unicloud.umeepay.dtos.admin.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.Administrator_;
import vn.unicloud.umeepay.enums.OfficeType;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class GetListAdminRequest extends BaseRequestData implements Specification<Administrator> {

    private String username;

    private String fullName;

    private String email;

    private String staffId;

    private OfficeType office;

    private UserStatus status;

    @JsonIgnore
    private Pageable pageable;


    @Override
    public Predicate toPredicate(Root<Administrator> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(username)) {
            predicates.add(builder.like(root.get(Administrator_.USERNAME), "%" + username + "%"));
        }

        if (StringUtils.isNotEmpty(fullName)) {
            predicates.add(builder.like(root.get(Administrator_.FULL_NAME), "%" + fullName + "%"));
        }

        if (StringUtils.isNotEmpty(email)) {
            predicates.add(builder.like(root.get(Administrator_.EMAIL), "%" + email + "%"));
        }

        if (StringUtils.isNotEmpty(staffId)) {
            predicates.add(builder.like(root.get(Administrator_.STAFF_ID), "%" + staffId + "%"));
        }

        if (Objects.nonNull(office)) {
            predicates.add(builder.equal(root.get(Administrator_.OFFICE), office));
        }

        if (Objects.nonNull(status)) {
            predicates.add(builder.equal(root.get(Administrator_.STATUS), status));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
