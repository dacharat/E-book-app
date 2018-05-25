package com.example.jack.ebook.presenter

import com.example.jack.ebook.models.Book
import com.example.jack.ebook.models.BookRepository
import java.util.*
import kotlin.collections.ArrayList

class BookPresenter(val view: BookView, val repository: BookRepository): Observer {

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }

    fun search(searchMsg: String, sortBy: String) {
        view.setBookList(repository.filterSorted(searchMsg, sortBy) as ArrayList<Book>)
    }

    fun addBook(bookList: ArrayList<Book>) {
        for(book in bookList)
            repository.addBook(book)
    }

    override fun update(o: Observable?, arg: Any?) {
        if(o == repository) {
            view.setBookList(repository.getBooks())
        }
    }
}