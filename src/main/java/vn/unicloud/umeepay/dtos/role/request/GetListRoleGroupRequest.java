package vn.unicloud.umeepay.dtos.role.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.entity.RoleGroup_;
import vn.unicloud.umeepay.enums.RoleStatus;
import vn.unicloud.umeepay.enums.RoleType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class GetListRoleGroupRequest extends BaseRequestData implements Specification<RoleGroup> {
    private String code;
    private String name;
    private RoleStatus status;
    private RoleType scope;

    @JsonIgnore
    private Pageable pageable;

    @Override
    public Predicate toPredicate(Root<RoleGroup> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.isNoneEmpty(code)) {
            predicates.add(builder.like(root.get(RoleGroup_.CODE), "%" + code + "%"));
        }

        if(StringUtils.isNoneEmpty(name)) {
            predicates.add(builder.like(root.get(RoleGroup_.NAME), "%" + name + "%"));
        }

        if(Objects.nonNull(status)) {
            predicates.add(builder.equal(root.get(RoleGroup_.STATUS), status));
        }

        if(Objects.nonNull(scope)) {
            predicates.add(builder.equal(root.get(RoleGroup_.SCOPE), scope));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
