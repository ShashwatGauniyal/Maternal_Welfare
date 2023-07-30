package sh.practice.chatbasedsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {


    private lateinit var editEmail :EditText
    private lateinit var editPassword :EditText
    private lateinit var btnLogin :Button
    private lateinit var btnSignup :Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        mAuth=FirebaseAuth.getInstance()
        editEmail=findViewById(R.id.edit_email)
        editPassword=findViewById(R.id.edit_password)
        btnLogin=findViewById(R.id.btn_login)
        btnSignup=findViewById(R.id.btn_signup)



        btnSignup.setOnClickListener{
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email =editEmail.text.toString()
            val password =editPassword.text.toString()

            login(email,password)
        }

    }
    private fun login(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent=Intent(this@LoginPage,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                Toast.makeText(this@LoginPage,"Login Failed.User dosen't exist",Toast.LENGTH_SHORT).show()
                }
            }
    }

}