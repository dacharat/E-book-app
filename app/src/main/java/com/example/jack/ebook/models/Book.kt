package com.example.jack.ebook.models

import java.io.Serializable

class Book(val id: Int, val title: String, val price: Double = 0.0, val pub_year: Int = 0, val img_url: String = ""): Serializable {

    override fun toString(): String {
        return String.format("%s (%d)", title, pub_year)
    }

}