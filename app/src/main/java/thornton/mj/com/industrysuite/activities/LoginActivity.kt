package thornton.mj.com.industrysuite.activities

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
import thornton.mj.com.industrysuite.R


/**
 * SUMMARY: Added an AlertManagerFragment (Dialog Fragment)
 *
 * 1. AlertManagerFragment is a DialogFragment that has a RecyclerView filled with
 * pre-populated messages to message the manager when clicked. Added a click listener,
 * waiting on having a managers application/DB side built out before the message / alert
 * is sent to the manager
 *
 * 2. Added a password view toggle on the LoginActivity
 *
 * 3. Added icons for profile and logout
 */
class LoginActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin = findViewById<Button>(R.id.email_sign_in_button)
        val btnRegister = findViewById<Button>(R.id.btn_register)

        val actv_username = findViewById<AutoCompleteTextView>(R.id.email)
        val et_password = findViewById<AutoCompleteTextView>(R.id.password)

        btnLogin.setOnClickListener { view ->
            signIn(view, actv_username.getText().toString(), et_password.getText().toString())
        }


        btnRegister.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, RegisteActivity::class.java)
            startActivity(intent)        })
    }

    fun signIn(view: View, email: String, password: String) {
        showMessage(view, "Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if (task.isSuccessful) {
                var intent = Intent(this, MainActivity::class.java)
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