package edu.uoc.pac2.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * An activity representing a list of Books.
 */
class BookListActivity : AppCompatActivity() {

    private val TAG = "BookListActivity"

    private lateinit var adapter: BooksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // Init UI
        initToolbar()
        initRecyclerView()

        // Get Books
        getBooks()

        // TODO: Add books data to Firestore [Use once for new projects with empty Firestore Database]
        //FirestoreBookData.addBooksDataToFirestoreDatabase()
    }

    // Init Top Toolbar
    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    // Init RecyclerView
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.book_list)
        // Set Layout Manager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // Init Adapter
        adapter = BooksListAdapter(emptyList()) {
            StartDetailActivity(it)
        }
        recyclerView.adapter = adapter
    }

    private fun StartDetailActivity(book: Book)
    {
        Log.w(TAG, "Clicked item.${book.uid}" )

        val intent = Intent(this, BookDetailActivity::class.java).apply {
            putExtra(BookDetailFragment.ARG_ITEM_ID, book.uid)
        }
        this.startActivity(intent)
    }

    // TODO: Get Books and Update UI
    private fun getBooks() {
        val myapp = (applicationContext as? MyApplication)

        //1 load data from local db
        loadBooksFromLocalDb()

        //2 if there is an avaliable connection, load from firestore
        if (myapp?.hasInternetConnection() == true) {
            Log.w(TAG, "Internet conection enabled. Load data from firestore.")
            loadBooksFromFirestore()
        }
    }

    // TODO: Load Books from Room
    private fun loadBooksFromLocalDb() {
        val myapp = (applicationContext as? MyApplication)
        var books: List<Book> = listOf()

        AsyncTask.execute {
           books = myapp?.getBooksInteractor()?.getAllBooks()!!
            runOnUiThread {
                adapter.setBooks(books)
            }
        }
    }

    private fun loadBooksFromFirestore()
    {
        val firestoreDatabase = Firebase.firestore
        firestoreDatabase
                .collection("books").addSnapshotListener { snapshots, e ->

                    if(e!=null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    var books: List<Book>? = snapshots?.documents?.mapNotNull {it.toObject (Book::class .java)}
                    Log.w(TAG, "Firestore data loaded.")
                    if (books == null) books = listOf()

                    //3 - Set books and save locally
                    adapter.setBooks(books)
                    saveBooksToLocalDatabase(books)
                }
    }

    private fun saveBooksToLocalDatabase(books: List<Book>) {
        val myapp = (applicationContext as? MyApplication)
        AsyncTask.execute{
            myapp?.getBooksInteractor()?.saveBooks(books = books)
        }
        runOnUiThread {

        }
    }
}