package io.quarkus.it.vthreads.kafka;

import static com.github.tomakehurst.wiremock.client.CountMatchingStrategy.GREATER_THAN_OR_EQUAL;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.http.RequestMethod.POST;
import static com.github.tomakehurst.wiremock.matching.RequestPatternBuilder.newRequestPattern;
import static org.awaitility.Awaitility.await;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.CountMatchingStrategy;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(WireMockExtension.class)
public class VirtualThreadTest {

    public static final int EXPECTED_CALLS = 10;
    WireMockServer mockServer;

    @Test
    void testAlert() {
        await().untilAsserted(() -> mockServer.verify(new CountMatchingStrategy(GREATER_THAN_OR_EQUAL, EXPECTED_CALLS),
                newRequestPattern(POST, urlPathEqualTo("/price/alert"))));
    }

    @Test
    @Disabled("flaky")
    void testAlertMessage() {
        await().untilAsserted(() -> mockServer.verify(new CountMatchingStrategy(GREATER_THAN_OR_EQUAL, EXPECTED_CALLS),
                newRequestPattern(POST, urlPathEqualTo("/price/alert-message"))));
    }

}
