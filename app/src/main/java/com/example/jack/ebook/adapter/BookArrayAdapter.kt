package com.example.jack.ebook.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.*
import com.example.jack.ebook.R
import com.example.jack.ebook.models.Book
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL


class BookArrayAdapter(val context: Context, val list: ArrayList<Book>, val isLineItem: Boolean = false): BaseAdapter(), ListAdapter{

    val cartList = ArrayList<Book>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            if(isLineItem)
                view = inflater.inflate(R.layout.lineitem, null)
            else
                view = inflater.inflate(R.layout.book_info, null)
        }

        val book: Book = list[position]
        //Handle TextView and display string from your list
        val bookTitleText = view!!.findViewById(R.id.book_title) as TextView
        bookTitleText.text = book.toString()

        val bookPriceText = view.findViewById(R.id.book_price) as TextView
        bookPriceText.text = "Price: " + book.price

        val bookImage = view.findViewById(R.id.book_image) as ImageView
        var input: InputStream? = null
        try {
            println(list[position].img_url+ "---------------------------------------------")
            val url = URL(list[position].img_url)
            input = BufferedInputStream(url.openStream())
            bookImage.setImageBitmap(BitmapFactory.decodeStream(input))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (input != null) {
                try {
                    input!!.close()
                } catch (ignored: IOException) {
                    // Nothing to do
                }

            }
        }

        if(isLineItem){
            val addBtn = view.findViewById(R.id.add_btn) as Button
            addBtn.setOnClickListener({
                val builder = AlertDialog.Builder(this.context)
                builder.setMessage("Add \"" + book.title + "\" to cart ?")
                        .setPositiveButton("ADD", DialogInterface.OnClickListener { dialog, id ->
                            if(!cartList.contains(book))
                                cartList.add(book)
                        })
                        .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
                builder.create().show()
                notifyDataSetChanged()
            })
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

}
