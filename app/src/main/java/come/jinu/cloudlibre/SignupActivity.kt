package come.jinu.cloudlibre

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import come.jinu.cloudlibre.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
	private lateinit var auth: FirebaseAuth
	private lateinit var signupBinding: ActivitySignupBinding


	@SuppressLint("ResourceAsColor")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		signupBinding = ActivitySignupBinding.inflate(layoutInflater)
		setContentView(signupBinding.root)
		auth = Firebase.auth
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor= ContextCompat.getColor(this, R.color.black)


		signupBinding.SignUpBtn.setOnClickListener {
			val email = signupBinding.emailTextUp.text.toString()
			val password = signupBinding.passTextUp.text.toString()
			val confirmPassword = signupBinding.coTextUp.text.toString()
			val username = signupBinding.nameText.text.toString()


			if(email.isNotEmpty() &&
				password.isNotEmpty() &&
				confirmPassword.isNotEmpty() &&
				username.isNotEmpty() &&
				password == confirmPassword &&
				password.length>=8
			)
				createAccount(email, password, username)

			else if (email.isEmpty()) signupBinding.emailCardUpInputLayout.error = "required..."
			else if(username.isEmpty())signupBinding.nameCardInputLayout.error = "required..."
			else if (password.length<8){
				signupBinding.passwordCardUpInputLayout.error = "8-16 characters required..."
				signupBinding.passTextUp.text?.clear()
				signupBinding.coTextUp.text?.clear()
			}
//			else if (!password.contains("[!-~]".toRegex())) signupBinding.passwordCardUpInputLayout.error = "!#$%&'()*+...,characters required..."
//			else if (!password.contains("[a-z]".toRegex())) signupBinding.passwordCardUpInputLayout.error = "lowercase letter required..."
//			else if (!password.contains("[A-Z]".toRegex())) signupBinding.passwordCardUpInputLayout.error = "Uppercase letter required..."
//			else if (!password.contains("[0-9]".toRegex())) signupBinding.passwordCardUpInputLayout.error = "numbers required..."


			else if(confirmPassword.isEmpty()||confirmPassword!=password)
			{
				signupBinding.coCardUpInputLayout.error = "required..."
				signupBinding.passTextUp.text?.clear()
				signupBinding.coTextUp.text?.clear()
			}
		}

		signupBinding.signin.setOnClickListener{
			startActivity(Intent(this,SigninActivity::class.java))
		}


	}

	private fun createAccount(email:String,password:String,username:String)
	{
		auth.createUserWithEmailAndPassword(email, password)
			.addOnCompleteListener(this) { task ->
				if (task.isSuccessful) {
					val user = Firebase.auth.currentUser

					val profileUpdates = userProfileChangeRequest {
						displayName = username
					}

					user!!.updateProfile(profileUpdates)
						.addOnCompleteListener { tasks ->
							if (tasks.isSuccessful) {
								Toast.makeText(baseContext, "Successful...", Toast.LENGTH_SHORT).show()
								signupBinding.passTextUp.text.clear()
								signupBinding.coTextUp.text.clear()
								signupBinding.emailTextUp.text.clear()
								signupBinding.nameText.text.clear()
								startActivity(Intent(this,MainActivity::class.java))
							}
						}
				} else {
					signupBinding.passTextUp.text.clear()
					signupBinding.coTextUp.text.clear()
					// If sign in fails, display a message to the user.
					Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
				}
			}

	}


}