package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    //start : OnCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //button click
        binding.btnLogin.setOnClickListener{
            //get values from input field
            val userId = binding.inputId.toString().trim()
            val userPwd = binding.inputPwd.toString().trim()

            //empty error
            if(userId.isEmpty()) //return true if Id is empty
            {
                binding.inputId.error = "Enter ID"
                binding.inputId.requestFocus()
            }else if (userPwd.isEmpty()) //return true Pwd is empty
            {
                binding.inputPwd.error= "Enter Pwd"
                binding.inputPwd.requestFocus()
            }else{ //validation is successful
                //open clothes activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
    //end : OnCreate
}