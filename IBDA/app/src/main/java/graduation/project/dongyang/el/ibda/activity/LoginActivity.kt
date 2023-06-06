package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    var errorCont = 0

    //start : OnCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(ButtonListener())
        binding.btnNotMember.setOnClickListener(ButtonListener())
        binding.btnHome.setOnClickListener (ButtonListener())

    }
    //end : OnCreate

    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()

            when (v?.id) {
                R.id.btnLogin->{
                    //empty error
                    val userId = binding.inputId.text.toString().trim()
                    val userPwd = binding.inputPwd.text.toString().trim()

                    if (userId.equals("admin") && userPwd.equals("1234")){
                        //회원 정보가 맞았을 경우
                        //의류 페이지 열기
                        intent=Intent(this@LoginActivity, MainClothesActivity::class.java)
                        intent.putExtra("id", userId)
                        intent.putExtra("password", userPwd)
                    }
                    else if(errorCont>3) { //로그인을 3회 이상 실패할 경우
                        binding.noticeErrorLogin.visibility=View.GONE
                        binding.noticeBackMain.visibility = View.VISIBLE
                        binding.btnNotMember.visibility = View.VISIBLE
                        return
                    }
                    else if(userId.isEmpty()) //아이디 칸이 비어있을 경우
                    {
                        errorCont++
                        binding.inputId.error = "아이디를 입력하세요."
                        binding.inputId.requestFocus()
                        return
                    }
                    else if(userPwd.isEmpty()) { //비밀번호 칸이 비어있을 경우
                        errorCont++
                        binding.inputPwd.error = "비밀번호를 입력하세요."
                        return
                    }
                    else{  //회원 정보가 없을 경우
                        errorCont++
                        binding.noticeErrorLogin.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.btnNotMember-> {
                    intent = Intent(this@LoginActivity, MainClothesActivity::class.java)
                    intent.putExtra("id", "비회원")
                }
                R.id.btnHome->{
                    intent=Intent(this@LoginActivity, MainActivity::class.java)
                }

            }
            startActivity(intent)
            finish()
        }
    }
}