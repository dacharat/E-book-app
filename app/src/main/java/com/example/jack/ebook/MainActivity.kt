package com.example.jack.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.jack.ebook.fragment.BookStoreFragment
import com.example.jack.ebook.fragment.MockUserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private val bookStoreFragment: BookStoreFragment = BookStoreFragment()
    private val mockUserFragment: MockUserFragment = MockUserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

//        mockUserFragment.addBook(bookStoreFragment.getCartList())
    }

    inner class SectionsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {

            when (position) {
                0 -> return bookStoreFragment
                1 -> return mockUserFragment
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }

}
