package com.liferay.read.confirmation.configuration

import com.mongodb.MongoClientSettings.Builder
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import kotlin.properties.Delegates

/**
 * @author Yasuyuki Takeo
 */
@EnableReactiveMongoRepositories
class MongoReactiveApplicationConfig() : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.database}")
    lateinit var database: String

    @Value("\${spring.data.mongodb.host}")
    lateinit var host: String

    @Value("\${spring.data.mongodb.username}")
    lateinit var username: String

    @Value("\${spring.data.mongodb.password}")
    lateinit var password: String

    @get:Value("\${spring.data.mongodb.port}")
    var port by Delegates.notNull<Int>()

    public override fun getDatabaseName(): String {
        return database
    }

    override fun configureClientSettings(builder: Builder) {
        builder
            .credential(MongoCredential.createCredential(username, database, password.toCharArray()))
            .applyToClusterSettings { settings ->
                settings.hosts(listOf(ServerAddress(host, port)))
            }
    }
}