package com.liferay.read.confirmation.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Yasuyuki Takeo
 */
@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Account(id: String, owner: String, value: Double) {
    @Id
    private val id: String = ""
    private val owner: String = ""
    private val value: Double = 0.0
}