package in.bushansirgur.springboot.repository;

import in.bushansirgur.springboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {

@Modifying
@Transactional
@Query("UPDATE Customer SET model=2001 where registration_number in (:reg_no)")
void updateTable(Integer[] reg_no);
@Query(value = "SELECT * FROM dbtable where registration_number=:a AND model=:b AND make=:c",nativeQuery = true)
Customer ut(@Param("a") int a, @Param("b") String b, @Param("c") long c);


}