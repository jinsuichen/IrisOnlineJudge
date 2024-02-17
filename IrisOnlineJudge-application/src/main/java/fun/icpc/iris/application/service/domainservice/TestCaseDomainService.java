package fun.icpc.iris.application.service.domainservice;

import fun.icpc.iris.application.dto.TestCaseDTO;
import fun.icpc.iris.domain.entity.table.TestCaseEntity;
import fun.icpc.iris.sharedkernel.util.IrisMessage;

public interface TestCaseDomainService {

    /**
     * Add a test case
     *
     * @param testCaseDTO testCaseDTO
     * @return TestCaseEntity
     */
    IrisMessage<TestCaseEntity> addTestCase(TestCaseDTO testCaseDTO);
}
