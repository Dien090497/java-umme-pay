package vn.unicloud.umeepay.dtos.paramter.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.entity.SystemParameter;
import vn.unicloud.umeepay.entity.SystemParameter_;
import vn.unicloud.umeepay.enums.SystemParameterGroup;
import vn.unicloud.umeepay.enums.SystemParameterType;

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
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GetAllSystemParamRequest extends BaseRequestData implements Specification<SystemParameter> {

    private SystemParameterGroup group;

    private SystemParameterType dataType;

    private String name;

    private String value;

    private Pageable pageable;

    @Override
    public Predicate toPredicate(Root<SystemParameter> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(group)) {
            predicates.add(builder.equal(root.get(SystemParameter_.GROUP), group));
        }

        if (Objects.nonNull(dataType)) {
            predicates.add(builder.equal(root.get(SystemParameter_.DATA_TYPE), dataType));
        }

        if (StringUtils.isNotBlank(name)) {
            predicates.add(builder.like(root.get(SystemParameter_.NAME), "%" + name.trim() + "%"));
        }

        if (StringUtils.isNotBlank(value)) {
            predicates.add(builder.like(root.get(SystemParameter_.VALUE), "%" + value.trim() + "%"));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
