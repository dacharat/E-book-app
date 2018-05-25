package com.example.jack.ebook.models

import java.util.*
import kotlin.collections.ArrayList

abstract class BookRepository: Observable() {

    val bookList = ArrayList<Book>()

    abstract fun loadAllBooks()
    
    fun getBooks(): ArrayList<Book> {
        return bookList
    }

    fun filterSorted(searchMsg: String, sortBy: String): List<Book> {
        var searchBook =  bookList.filter { book: Book ->
            book.title.contains(searchMsg, true) || book.pub_year.toString().contains(searchMsg, true)
        }

        when (sortBy) {
            "UNSORTED" -> return searchBook
            "TITLE" -> searchBook = ArrayList(searchBook.sortedWith(compareBy({it.title})))
            "YEAR" -> searchBook = ArrayList(searchBook.sortedWith(compareBy({it.pub_year})))
        }

        return searchBook
    }

    fun addBook(book: Book){
        bookList.add(book)
        setChanged()
        notifyObservers()
    }
}