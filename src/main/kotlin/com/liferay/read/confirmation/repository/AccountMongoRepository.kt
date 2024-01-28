package com.liferay.read.confirmation.repository

import com.liferay.read.confirmation.model.Account
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

/**
 * @author Yasuyuki Takeo
 */
interface AccountMongoRepository : ReactiveMongoRepository<Account?, String?>