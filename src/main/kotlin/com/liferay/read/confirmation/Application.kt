package com.liferay.read.confirmation

import com.mongodb.reactivestreams.client.MongoClient
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.ReactiveMongoTemplate

/**
 * @author Yasuyuki Takeo
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = io.swagger.v3.oas.annotations.info.Info(
                title = "Read Confirmation Service",
                version = "1.0.0",
                description = "Read Confirmation Service for Liferay 7.4"
        )
)
class Application {
    @Autowired
    var mongoClient: MongoClient? = null

    @Bean
    fun reactiveMongoTemplate(): ReactiveMongoTemplate? {
        return mongoClient?.let { ReactiveMongoTemplate(it, "test") }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
