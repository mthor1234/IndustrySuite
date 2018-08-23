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
 * SUMMARY:
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