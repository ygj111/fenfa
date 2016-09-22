package com.hhh.dao.fenfa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsOrder;

@Repository
public interface SsOrderDao extends PagingAndSortingRepository<SsOrder, String>, JpaSpecificationExecutor<SsOrder>{
    Page<SsOrder> findByStatus(String status, Pageable pageable);
//    Page<SsOrder> findByStatusOrStatus(String status, String Status,Pageable pageable);
    Page<SsOrder> findByStatusOrStatusOrStatus(String status, String Status, String Status3,Pageable pageable);
    public Page<SsOrder> findByCustomerId(String customerId,Pageable pageable);
    public Page<SsOrder> findByCompanyIdOrderByOrderDateDesc(String companyId,Pageable pageable);
    @Modifying
    @Query("update SsOrder set customerId=:customerId where id=:id")
    public void updateCustomerId(@Param("customerId")String customerId,@Param("id")String id);
    @Modifying
    @Query("update SsOrder set status=:status where id=:id")
    public void updateStatus(@Param("status")String status,@Param("id")String id);
    
    @Query("select o from SsOrder o where o.status=:status1 or o.status=:status2")
    public Page<SsOrder> findByStatusOrStatus(@Param("status1")String status1, @Param("status2")String status2,Pageable pageable);
}
