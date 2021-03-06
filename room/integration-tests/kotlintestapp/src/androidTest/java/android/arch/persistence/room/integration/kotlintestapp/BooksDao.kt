/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.arch.persistence.room.integration.kotlintestapp

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.integration.kotlintestapp.vo.*
import io.reactivex.Flowable

@Dao
interface BooksDao {

    @Insert
    fun addPublishers(vararg publishers: Publisher)

    @Insert
    fun addAuthors(vararg authors: Author)

    @Insert
    fun addBooks(vararg books: Book)

    @Insert
    fun addBookAuthors(vararg bookAuthors: BookAuthor)

    @Query("SELECT * FROM book WHERE bookId = :bookId")
    fun getBook(bookId: String): Book

    @Query("SELECT * FROM book WHERE bookId = :bookId")
    fun getBookLiveData(bookId: String): LiveData<Book>

    @Query("SELECT * FROM book WHERE bookId = :bookId")
    fun getBookFlowable(bookId: String): Flowable<Book>

    @Query("SELECT * FROM book INNER JOIN publisher " +
            "ON book.bookPublisherId = publisher.publisherId ")
    fun getBooksWithPublisher(): List<BookWithPublisher>

    @Query("SELECT * FROM book INNER JOIN publisher " +
            "ON book.bookPublisherId = publisher.publisherId ")
    fun getBooksWithPublisherLiveData(): LiveData<List<BookWithPublisher>>

    @Query("SELECT * FROM book INNER JOIN publisher " +
            "ON book.bookPublisherId = publisher.publisherId ")
    fun getBooksWithPublisherFlowable(): Flowable<List<BookWithPublisher>>

    @Query("SELECT * FROM publisher WHERE publisherId = :publisherId")
    fun getPublisherWithBooks(publisherId: String): PublisherWithBooks

    @Query("SELECT * FROM publisher WHERE publisherId = :publisherId")
    fun getPublisherWithBooksLiveData(publisherId: String): LiveData<PublisherWithBooks>

    @Query("SELECT * FROM publisher WHERE publisherId = :publisherId")
    fun getPublisherWithBooksFlowable(publisherId: String): Flowable<PublisherWithBooks>
}
