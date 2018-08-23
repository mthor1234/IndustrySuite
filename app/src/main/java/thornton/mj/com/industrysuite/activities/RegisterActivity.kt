package thornton.mj.com.industrysuite.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import thornton.mj.com.industrysuite.R

/**
 * Created by mitchthornton on 8/20/18.
 */

private val tag = "myLogTag"

//UI elements
private var etFirstName: EditText? = null
private var etLastName: EditText? = null
private var etEmail: EditText? = null
private var etPhone: EditText? = null
private var etPassword: EditText? = null
private var btnCreateAccount: Button? = null
private var mProgressBar: ProgressDialog? = null

//Firebase references
private var mDatabaseReference: DatabaseReference? = null
private var mDatabase: FirebaseDatabase? = null
private var mAuth: FirebaseAuth? = null


//global variables
private var firstName: String? = null
private var lastName: String? = null
private var email: String? = null
private var phone: String? = null
private var password: String? = null


class RegisteActivity : AppCompatActivity() {


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)
    initialise()
}
private fun initialise() {
    etFirstName = findViewById<View>(R.id.et_first_name) as EditText
    etLastName = findViewById<View>(R.id.et_last_name) as EditText
    etEmail = findViewById<View>(R.id.et_email) as EditText
    etPhone = findViewById<View>(R.id.et_phone) as EditText
    etPassword = findViewById<View>(R.id.et_password) as EditText
    btnCreateAccount = findViewById<View>(R.id.btn_register) as Button
    mProgressBar = ProgressDialog(this)
    mDatabase = FirebaseDatabase.getInstance()
    mDatabaseReference = mDatabase!!.reference!!.child("Users")
    mAuth = FirebaseAuth.getInstance()
    btnCreateAccount!!.setOnClickListener { createNewAccount() }
}

private fun createNewAccount() {
    firstName = etFirstName?.text.toString()
    lastName = etLastName?.text.toString()
    email = etEmail?.text.toString()
    phone = etPhone?.text.toString()
    password = etPassword?.text.toString()

    if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
            && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

        mAuth!!.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    mProgressBar!!.hide()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(tag, "createUserWithEmail:success")
                        val userId = mAuth!!.currentUser!!.uid
                        //Verify Email
                        verifyEmail();
                        //update user profile information
                        val currentUserDb = mDatabaseReference!!.child(userId)
                        currentUserDb.child("firstName").setValue(firstName)
                        currentUserDb.child("lastName").setValue(lastName)
//                        currentUserDb.child("phone").setValue(phone)
                        updateUserInfoAndUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(tag, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }


                }
    }else {
        Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
    }

    mProgressBar!!.setMessage("Registering User...")
    mProgressBar!!.show()
}

    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,
                                "Verification email sent to " + mUser.getEmail(),
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e(tag, "sendEmailVerification", task.exception)
                        Toast.makeText(this,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

}

