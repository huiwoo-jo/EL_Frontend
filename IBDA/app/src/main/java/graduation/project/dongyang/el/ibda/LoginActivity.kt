package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

            val userId = binding.inputId.text.toString().trim()
            val userPwd = binding.inputPwd.text.toString().trim()

            //empty error
            if (userId.equals("admin") && userPwd.equals("1234")){
                //회원 정보가 맞았을 경우
                //의류 페이지 열기
                val intent = Intent(this, LoginTest::class.java)
                intent.putExtra("id", userId)
                intent.putExtra("password", userPwd)
                startActivity(intent)
                finish()
            }
            else if(userId.isEmpty()) //아이디 칸이 비어있을 경우
            {
                binding.inputId.error = "아이디를 입력하세요."
                binding.inputId.requestFocus()
            }
            else if(userPwd.isEmpty()){ //비밀번호 칸이 비어있을 경우
                binding.inputPwd.error= "비밀번호를 입력하세요."
                binding.inputPwd.requestFocus()
            }
            else{  //회원 정보가 없을 경우
                binding.loginError.visibility = View.VISIBLE
            }
        }

    }
    //end : OnCreate
}