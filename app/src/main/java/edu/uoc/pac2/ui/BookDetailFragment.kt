package edu.uoc.pac2.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book

import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */
class BookDetailFragment : Fragment() {
    private val TAG = "BookDetailFragment"
    private var currentBook: Book? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_book_detail, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get Book for this detail screen
        loadBook(view)
    }


    // TODO: Get Book for the given {@param ARG_ITEM_ID} Book id
    private fun loadBook(view: View) {
        //val id = intent.getStringExtra(BookDetailFragment.ARG_ITEM_ID)
        Log.i(TAG, "loadBook()")
        //var currentBook: Book?
        if (arguments?.containsKey(ARG_ITEM_ID)!!) {
            AsyncTask.execute {
                val myapp = (activity?.applicationContext as? MyApplication)
                currentBook = myapp?.getBooksInteractor()?.getBookById(arguments?.getInt(ARG_ITEM_ID)!!)
                Log.i(TAG, "loadBook() -> Book Tittle ${currentBook?.title}")

                if(currentBook != null){
                    initUI(currentBook!!)
                    activity?.toolbar_layout?.title=currentBook?.title
                }

                currentBook?.let {
                    view.book_author.text = it.author
                    view.book_date.text = it.publicationDate
                    view.book_detail.text = it.description
                    activity?.runOnUiThread {
                        Glide.with(this).load(it.urlImage).into(view.book_image)
                    }

                }
            }
        }
    }

    // TODO: Init UI with book details
    private fun initUI(book: Book) {



    }

    // TODO: Share Book Title and Image URL
    private fun shareContent(book: Book) {
        throw NotImplementedError()
    }

    companion object {
        /**
         * The fragment argument representing the item title that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "itemIdKey"

        fun newInstance(itemId: Int): BookDetailFragment {
            val fragment = BookDetailFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_ITEM_ID, itemId)
            fragment.arguments = arguments
            return fragment
        }
    }
}