package com.example.jack.ebook.presenter

import com.example.jack.ebook.models.Book

interface BookView {
     fun setBookList(books: ArrayList<Book>)
}
