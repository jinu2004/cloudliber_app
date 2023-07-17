package come.jinu.cloudlibre

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import come.jinu.cloudlibre.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
	private var pressedTime = 0
	private lateinit var auth: FirebaseAuth
	private lateinit var signinBinding: ActivitySigninBinding
	@RequiresApi(Build.VERSION_CODES.S)
	@SuppressLint("InflateParams", "ResourceAsColor")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		signinBinding = ActivitySigninBinding.inflate(layoutInflater)
		setContentView(signinBinding.root)
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor= ContextCompat.getColor(this, R.color.black)
		auth = Firebase.auth

		signinBinding.SigninBtn.setOnClickListener{

			val email = signinBinding.emailTextIn.text.toString()
			val password = signinBinding.passwordCardInput.text.toString()





			if (email.isNotEmpty() && password.isNotEmpty())
				signin(email,password)

			else if (email.isEmpty()) {
				signinBinding.emailCardInInputLayout.error = "required"

			}

			else if (password.isEmpty())
				signinBinding.passwordCardInputLayout.apply {
					error = "required"
				}

		}




		signinBinding.signin.setOnClickListener{
			startActivity(Intent(this,SignupActivity::class.java))
		}

		signinBinding.forgotPassword.setOnClickListener{
			startActivity(Intent(this,Forgot_password::class.java))
		}

		signinBinding.googleIn.setOnClickListener {

		}



	//////
	}

	public override fun onStart() {
		super.onStart()
		val currentUser = auth.currentUser
		if(currentUser!=null)startActivity(Intent(this,MainActivity::class.java))
	}



	@Suppress("DEPRECATION")
	@Deprecated("Deprecated in Java")
	override fun onBackPressed() {
		super.onBackPressed()
			finishAffinity()
	}

	private fun signin(email:String,password:String){
		auth.signInWithEmailAndPassword(email,password)
			.addOnCompleteListener(this) { task ->
				if (task.isSuccessful) {
					val username = auth.currentUser?.displayName.toString()

					Toast.makeText(baseContext, "Welcome back $username",
						Toast.LENGTH_SHORT).show()
					startActivity(Intent(this,MainActivity::class.java))
				} else {
					Toast.makeText(baseContext, "please enter valid email and password...",
						Toast.LENGTH_SHORT).show()
						signinBinding.passwordCardInput.text?.clear()
				}
			}
	}


}