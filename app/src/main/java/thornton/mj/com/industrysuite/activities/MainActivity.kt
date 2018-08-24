package thornton.mj.com.industrysuite.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import thornton.mj.com.industrysuite.R
import thornton.mj.com.industrysuite.fragments.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        fbAuth.addAuthStateListener {
            if(fbAuth.currentUser == null){
                this.finish()
            }
        }

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.home -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, HomeFragment.newInstance())
                        .commit()
            }
            R.id.schedule -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, ScheduleFragment.newInstance())
                        .commit()
            }
            R.id.guestlist -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, GuestListFragment.newInstance())
                        .commit()
            }
            R.id.contacts -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, ContactsFragment.newInstance())
                        .commit()
            }
            R.id.security -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, SecurityFragment.newInstance())
                        .commit()
            } R.id.signal_management -> {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content, AlertManagerFragment.newInstance())
                    .commit()
            }
            R.id.profile -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, ProfileFragment.newInstance())
                        .commit()
            }
            R.id.logout -> {
                showMessage(content, "Logging Out...")
                signOut()
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun signOut(){
        fbAuth.signOut()

    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }
}
