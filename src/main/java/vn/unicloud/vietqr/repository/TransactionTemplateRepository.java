package vn.unicloud.vietqr.repository;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import vn.unicloud.vietqr.entity.Transaction;
import vn.unicloud.vietqr.enums.TransactionStatus;
import vn.unicloud.vietqr.model.RecordFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Log4j2
public class TransactionTemplateRepository {

    private static SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public List getTransaction(Pageable pageable, RecordFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> q = cb.createQuery(Transaction.class);

        org.hibernate.Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Criteria criteria
                = session.createCriteria(Transaction.class);
            if (StringUtils.isNotEmpty(filter.getKeyword())) {
                criteria.add(
                    Restrictions.or(
                        Restrictions.ilike("terminal_id", filter.getKeyword()),
                        Restrictions.ilike("customer_phone", filter.getKeyword()),
                        Restrictions.ilike("trace_id", filter.getKeyword()),
                        Restrictions.ilike("customer_id_card_no", filter.getKeyword())
                    )
                );
            }
            if (filter.getStatus() != null && !filter.getStatus().equals(TransactionStatus.NONE)) {
                criteria.add(Restrictions.eq("status", filter.getStatus().toString()));
            }
            if (StringUtils.isNotEmpty(filter.getFromDate()) && StringUtils.isNotEmpty(filter.getToDate())) {
                criteria.add(Restrictions.and(
                    Restrictions.ge("create_date", filter.getFromDate()),
                    Restrictions.le("create_date", filter.getToDate())
                ));
            }
            criteria.setTimeout(10000);
            List res = criteria.list();
            tx.commit();
            return res;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return null;
    }

}
