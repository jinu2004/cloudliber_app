package come.jinu.cloudlibre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import come.jinu.cloudlibre.databinding.ActivityForgotPasswordBinding

class Forgot_password : AppCompatActivity() {
	private lateinit var binding: ActivityForgotPasswordBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_forgot_password)
		binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
		setContentView(binding.root)
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor= ContextCompat.getColor(this, R.color.black)

		binding.send.setOnClickListener {
			if (binding.emailTextFor.text.toString().isNotEmpty())
				resetPassword(binding.emailTextFor.text.toString())
			else
				binding.nameCardInputLayout.error = "fill this field"
		}



	}

	private fun resetPassword(email: String){
		Firebase.auth.sendPasswordResetEmail(email).addOnCompleteListener { task->
			if (task.isSuccessful){
				Toast.makeText(baseContext, "check your mail... ", Toast.LENGTH_SHORT).show()
				startActivity(Intent(this,SigninActivity::class.java))
			}
		}
	}
}