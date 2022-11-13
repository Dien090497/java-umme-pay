package vn.unicloud.umeepay.dtos.paramter.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Sort;
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

    @JsonIgnore
    private Sort sort;

    @Override
    public Predicate toPredicate(Root<SystemParameter> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(group)) {
            predicates.add(builder.equal(root.get(SystemParameter_.GROUP), group));
        }

        if (Objects.nonNull(dataType)) {
            predicates.add(builder.equal(root.get(SystemParameter_.DATA_TYPE), dataType));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
