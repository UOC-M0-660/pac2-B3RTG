package edu.uoc.pac2.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import edu.uoc.pac2.R
import kotlinx.android.synthetic.main.activity_book_detail.*

/**
 * An activity representing a single Book detail screen.
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.translate_in_bottom, R.anim.translate_in_bottom)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(detail_toolbar)

        /*fav.setOnClickListener { view ->

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }*/

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val itemID = intent.getIntExtra(BookDetailFragment.ARG_ITEM_ID, -1)
            val fragment = BookDetailFragment.newInstance(itemID)
            supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit()
        }
    }

    // TODO: Override finish animation for actionbar back arrow
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        overridePendingTransition(R.anim.translate_out_top, R.anim.translate_in_top)
        return super.onOptionsItemSelected(item)
    }

    // TODO: Override finish animation for phone back button
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.translate_out_top, R.anim.translate_in_top)
    }

}