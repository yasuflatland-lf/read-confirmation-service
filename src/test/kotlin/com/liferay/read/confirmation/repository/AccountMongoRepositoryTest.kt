package com.liferay.read.confirmation.repository

import com.liferay.read.confirmation.model.Account
import io.kotest.core.annotation.DoNotParallelize
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers
import com.liferay.read.confirmation.AbstractContainerBaseTest

@Testcontainers
@SpringBootTest(properties = ["spring.main.web-application-type=reactive"], args = ["-opt-in=kotlin.RequiresOptIn"])
@DoNotParallelize
@ActiveProfiles("test")
@ContextConfiguration(initializers = [AbstractContainerBaseTest.Initializer::class])
class AccountMongoRepositoryTest : FunSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var repository: AccountMongoRepository

    init {
        beforeEach {
            repository.deleteAll()
        }

        test("Save") {
            repository.save(Account("", "Bill", 12.3)).block()
        }
    }
}