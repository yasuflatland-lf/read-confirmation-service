package com.liferay.read.confirmation.template

import com.liferay.read.confirmation.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.ReactiveRemoveOperation
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


/**
 * @author Yasuyuki Takeo
 */
@Service
class AccountTemplateOperations {
    @Autowired
    var template: ReactiveMongoTemplate? = null

    fun findById(id: String?): Mono<Account> {
        return template!!.findById(id!!, Account::class.java)
    }

    fun findAll(): Flux<Account> {
        return template!!.findAll(Account::class.java)
    }

    fun save(account: Mono<Account>?): Mono<Account>? {
        return account?.let { template!!.save(it) }
    }

    fun deleteAll(): ReactiveRemoveOperation.ReactiveRemove<Account> {
        return template!!.remove(Account::class.java)
    }
}