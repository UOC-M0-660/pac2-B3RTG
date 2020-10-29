package edu.uoc.pac2.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book

import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */
class BookDetailFragment (

): Fragment() {
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
        loadBook()

        activity?.fav?.setOnClickListener { view ->
           if(currentBook!=null) shareContent(currentBook!!)
        }
    }

    private fun loadBook() {
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
                }

            }
        }
    }

    private fun initUI(book: Book) {
        book.let {
            book_author.text = it.author
            book_date.text = it.publicationDate
            book_detail.text = it.description
            activity?.runOnUiThread {
                Glide.with(this).load(it.urlImage).into(book_image)
                //Glide.with(this).load(it.urlImage).into(activity?.toolbar_layout?.background)

                Glide.with(this).load(it.urlImage).into(object :
                        CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                activity?.toolbar_layout?.background =  resource
                            }

                        })

            }
            activity?.toolbar_layout?.title=currentBook?.title


        }
    }

    private fun shareContent(book: Book) {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${book.title}\n${book.urlImage}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Book info share")
        startActivity(shareIntent)
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