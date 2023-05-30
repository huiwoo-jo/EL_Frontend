package kr.project.dongyang.el.ibda.ibda_admin

import android.content.Intent
import android.icu.util.Measure
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24_white)  // 왼쪽 버튼 아이콘 설정

        //버튼
        binding.mainBtnMembers.setOnClickListener(ButtonListener())
        binding.mainBtnClothes.setOnClickListener(ButtonListener())
        binding.mainBtnInventory.setOnClickListener(ButtonListener())
        binding.mainBtnSetting.setOnClickListener(ButtonListener())
    }

    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.drawable.ic_baseline_home_24_white -> {
                //안드로이드 홈화면 눌렀을 때
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()

            when (v?.id) {
                R.id.main_btn_members -> {
                    // 메인 -> 회원 관리
                }
                R.id.main_btn_clothes -> {
                    //메인 -> 의류 관리
                    intent = Intent(this@MainActivity, MainClothes::class.java)
                }

                R.id.main_btn_inventory->{
                    // 메인 -> 재고 관리
                }
                R.id.main_btn_setting->{
                    // 메인 -> 계정 관리
                }
            }
            startActivity(intent)
            finish()
        }
    }
}