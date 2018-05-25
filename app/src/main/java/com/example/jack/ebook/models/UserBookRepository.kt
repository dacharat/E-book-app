package com.example.jack.ebook.models

class UserBookRepository: BookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
    }

}