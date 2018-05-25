package com.example.jack.ebook.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.jack.ebook.R
import com.example.jack.ebook.adapter.BookArrayAdapter
import com.example.jack.ebook.models.Book
import com.example.jack.ebook.models.BookRepository
import com.example.jack.ebook.models.MockBookRepository
import com.example.jack.ebook.presenter.BookPresenter
import com.example.jack.ebook.presenter.BookView
import kotlinx.android.synthetic.main.booklist_fragment.*
import kotlinx.android.synthetic.main.booklist_fragment.view.*

class BookStoreFragment : Fragment(), BookView {

    private var listViewAdapter: BookArrayAdapter? = null
    private var spinnerAdapter: ArrayAdapter<String>? = null
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.booklist_fragment, container, false)
        repository = MockBookRepository()
        presenter = BookPresenter(this, repository)
        presenter = BookPresenter(this, repository)
        presenter.start()
        spinnerAdapter = ArrayAdapter(rootView.context,android.R.layout.simple_list_item_1)
        setSearchView()
        setSpinner()
        return rootView
    }

    override fun setBookList(books: ArrayList<Book>) {
        listViewAdapter = BookArrayAdapter(rootView.context, books, true)
        rootView.bookList.adapter = listViewAdapter
    }

    fun getCartList(): ArrayList<Book> {
        return listViewAdapter!!.cartList
    }

    private fun setSearchView() {
        rootView.searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    presenter.search(searchView.query.toString(), spinner.selectedItem.toString())
                return false
            }
        })
    }

    private fun setSpinner() {
        spinnerAdapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_item, arrayListOf("UNSORTED", "TITLE", "YEAR"))
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rootView.spinner.adapter = spinnerAdapter
        rootView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.search(searchView.query.toString(), spinner.selectedItem.toString())
            }
        }
    }

}
