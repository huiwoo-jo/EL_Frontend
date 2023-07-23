package kr.project.dongyang.el.ibda.ibda_client.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.project.dongyang.el.ibda.ibda_client.R
import kr.project.dongyang.el.ibda.ibda_client.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    var errorCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(ButtonListener())
    }
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()

            when (v?.id) {
                R.id.btnLogin ->{
                    //empty error
                    val userId = binding.inputId.text.toString().trim()
                    val userPwd = binding.inputPwd.text.toString().trim()

                    if (userId.equals("admin") && userPwd.equals("1234")){
                        //회원 정보가 맞았을 경우
                        //의류 페이지 열기
                        intent= Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("id", userId)
                        intent.putExtra("password", userPwd)
                    }
                    else if(userId.isEmpty()) //아이디 칸이 비어있을 경우
                    {
                        errorCount++
                        binding.inputId.error = "아이디를 입력하세요."
                        binding.inputId.requestFocus()
                        return
                    }
                    else if(userPwd.isEmpty()) { //비밀번호 칸이 비어있을 경우
                        errorCount++
                        binding.inputPwd.error = "비밀번호를 입력하세요."
                        return
                    }
                    else{  //회원 정보가 없을 경우
                        errorCount++
                        return
                    }
                }
            }
            startActivity(intent)
            finish()
        }
    }
}