package com.example.jack.ebook.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.jack.ebook.R
import com.example.jack.ebook.adapter.BookArrayAdapter
import com.example.jack.ebook.models.Book
import com.example.jack.ebook.models.BookRepository
import com.example.jack.ebook.models.UserBookRepository
import com.example.jack.ebook.presenter.BookPresenter
import com.example.jack.ebook.presenter.BookView
import kotlinx.android.synthetic.main.user_fragment.*
import kotlinx.android.synthetic.main.user_fragment.view.*

class MockUserFragment: Fragment(), BookView {

    private var listViewAdapter: BookArrayAdapter? = null
    private var spinnerAdapter: ArrayAdapter<String>? = null
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.user_fragment, container, false)
        repository = UserBookRepository()
        presenter = BookPresenter(this, repository)
        presenter.start()
        spinnerAdapter = ArrayAdapter(rootView.context,android.R.layout.simple_list_item_1)
        return rootView
    }

    override fun setBookList(books: ArrayList<Book>) {
        listViewAdapter = BookArrayAdapter(rootView.context, books)
        rootView.bookList1.adapter = listViewAdapter
    }
}