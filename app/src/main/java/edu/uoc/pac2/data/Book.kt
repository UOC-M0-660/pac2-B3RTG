package edu.uoc.pac2.data

import android.accounts.AuthenticatorDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A book Model representing a piece of content.
 */
@Entity(tableName="books")
data class Book(
        val title: String? = null,
        val author: String? = null,
        val description: String? = null,
        val publicationDate: String? = null,
        @PrimaryKey(autoGenerate = true)
        val uid: Int? = null,
        val urlImage: String? = null
)