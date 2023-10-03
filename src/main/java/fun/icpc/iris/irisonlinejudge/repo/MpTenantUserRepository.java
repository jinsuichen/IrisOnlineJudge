package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MpTenantUserRepository extends JpaRepository<MpTenantUser, Long> {

    List<MpTenantUser> findByTenant_IdAndUser_Id(Long tenantId, Long userId);

}
