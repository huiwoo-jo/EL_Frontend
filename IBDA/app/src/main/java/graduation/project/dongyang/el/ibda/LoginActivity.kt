package graduation.project.dongyang.el.ibda

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    //start : OnCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, LoginTest::class.java)
        var errorCont = 0

        //button click
        binding.btnLogin.setOnClickListener{
            //get values from input field
            //empty error
            val userId = binding.inputId.text.toString().trim()
            val userPwd = binding.inputPwd.text.toString().trim()

            if (userId.equals("admin") && userPwd.equals("1234")){
                //회원 정보가 맞았을 경우
                //의류 페이지 열기
                intent.putExtra("id", userId)
                intent.putExtra("password", userPwd)
                startActivity(intent)
                finish()
            }
            else if(errorCont>3) { //로그인을 3회 이상 실패할 경우
                binding.noticeErrorLogin.visibility=View.GONE
                binding.noticeBackMain.visibility = View.VISIBLE
                binding.btnNotMember.visibility = View.VISIBLE
            }
            else if(userId.isEmpty()) //아이디 칸이 비어있을 경우
            {
                errorCont++
                binding.inputId.error = "아이디를 입력하세요."
                binding.inputId.requestFocus()
            }
            else if(userPwd.isEmpty()) { //비밀번호 칸이 비어있을 경우
                errorCont++
                binding.inputPwd.error = "비밀번호를 입력하세요."
                binding.inputPwd.requestFocus()
            }
            else{  //회원 정보가 없을 경우
                errorCont++
                binding.noticeErrorLogin.visibility = View.VISIBLE
            }
        }

        binding.btnNotMember.setOnClickListener{
            //비회원으로 시작
            startActivity(intent)
            finish()
        }

        

    }
    //end : OnCreate
}