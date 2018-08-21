package thornton.mj.com.industrysuite

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


/**
 * A login screen that offers login via email/password.
 *
 * SUMMARY: Set up a Firebase Email/Password User Authentication system
 *  1) Username and password values are compared against the Firebase User database.
 *  If the user is present, then it will launch the LoggedInActivity,
 *  Otherwise, it will display an incorrect username/password to the user
 */
class LoginActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin = findViewById<Button>(R.id.email_sign_in_button)
        val actv_username = findViewById<AutoCompleteTextView>(R.id.email)
        val et_password = findViewById<AutoCompleteTextView>(R.id.password)

        btnLogin.setOnClickListener { view ->
            signIn(view, actv_username.getText().toString(), et_password.getText().toString())
        }
    }

    fun signIn(view: View, email: String, password: String) {
        showMessage(view, "Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if (task.isSuccessful) {
                var intent = Intent(this, LoggedInActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)

            } else {
                showMessage(view, "Error: ${task.exception?.message}")
            }
        })
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }
}