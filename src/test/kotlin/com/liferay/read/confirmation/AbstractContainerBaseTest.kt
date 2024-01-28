package com.liferay.read.confirmation

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Testcontainers
abstract class AbstractContainerBaseTest {

    companion object {
        val log: Logger = LoggerFactory.getLogger(AbstractContainerBaseTest::class.java)

        val mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:latest"))
                .apply {
                    start()
                }
    }

    class Initializer() : ApplicationContextInitializer<ConfigurableApplicationContext> {
        companion object {
            val log: Logger = LoggerFactory.getLogger(Initializer::class.java)
        }

        override fun initialize(applicationContext: ConfigurableApplicationContext) {

            TestPropertyValues.of(
                "spring.data.mongodb.uri=${mongoDBContainer.connectionString}",
                "spring.data.mongodb.port=${mongoDBContainer.exposedPorts[0]}"
            ).applyTo(applicationContext.environment)
        }
    }
}