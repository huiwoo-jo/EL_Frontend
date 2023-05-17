package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityDetailClothesBinding

class DetailClothes : AppCompatActivity() {

    private lateinit var binding: ActivityDetailClothesBinding
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailClothesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        super.onCreate(savedInstanceState)

        //툴바
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //즐겨찾기 버튼
        val favoriteBtn = binding.btnFavorite

        favoriteBtn.setOnClickListener {
            isFavorite = !isFavorite
            updateFavoriteButtonState()

        }

    }

    private fun updateFavoriteButtonState() {
        //즐겨찾기 추가된 상태
        if (isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.selected_favorite)
        } else { //즐겨찾기 해제된 상태
            binding.btnFavorite.setImageResource(R.drawable.unselected_favorite)
        }
    }


    //액션바에 액션 버튼 삽입
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_home -> {
                //안드로이드 홈화면 눌렀을 때
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
                finish()
            }

            R.id.action_info -> {
                val popup = PopupFragment()
                popup.show(supportFragmentManager, "popup")
            }

        }
        return super.onOptionsItemSelected(item)
    }
}