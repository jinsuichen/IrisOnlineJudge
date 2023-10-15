package fun.icpc.iris.irisonlinejudge.service.domainservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.dto.TestCaseDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TestCaseEntity;

public interface TestCaseDomainService {

    /**
     * Add a test case
     *
     * @param testCaseDTO testCaseDTO
     * @return TestCaseEntity
     */
    IrisMessage<TestCaseEntity> addTestCase(TestCaseDTO testCaseDTO);
}
