package com.naleid

import ratpack.rx.RxRatpack
import ratpack.test.exec.ExecHarness
import spock.lang.Specification

class IpServiceSpec extends Specification {

    CollaboratorService collaboratorServiceMock

    def setup() {
        RxRatpack.initialize()
        collaboratorServiceMock = Mock(CollaboratorService)
    }

    def "MyIpService extracts the IP from the returned JSON"() {
        given:
        String expectedValue = "test value"
        collaboratorServiceMock.findValue() >> expectedValue

        when:
        String result = ExecHarness.yieldSingle({ execution ->
            new IpService(collaboratorServiceMock).lookupResult().x(RxRatpack.&promiseSingle)
        }).valueOrThrow

        then:
        result == expectedValue

    }
}
