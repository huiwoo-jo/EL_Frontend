package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityAiRecommendBinding
import kr.project.dongyang.EL.IBDA.databinding.ActivityArFittingactivityBinding

class AR_fittingActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityArFittingactivityBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener(ButtonListener())
        binding.btnBackMenu.setOnClickListener(ButtonListener())
        binding.btnBackMenu2.setOnClickListener(ButtonListener())
        binding.btnBackMenu3.setOnClickListener(ButtonListener())


        binding.btnTops.setOnClickListener(ButtonListener())
        binding.btnPants.setOnClickListener(ButtonListener())
        binding.btnDress.setOnClickListener(ButtonListener())

        binding.btnTop1.setOnClickListener(ButtonListener())
        binding.btnTop2.setOnClickListener(ButtonListener())
        binding.btnTop3.setOnClickListener(ButtonListener())

        binding.btnPants1.setOnClickListener(ButtonListener())
        binding.btnPants2.setOnClickListener(ButtonListener())
        binding.btnPants3.setOnClickListener(ButtonListener())

        binding.btnDress1.setOnClickListener(ButtonListener())

    }

    // 버튼 클릭
    inner class ButtonListener(): View.OnClickListener {
        override fun onClick(v: View?) {
            binding.rvMenu.visibility = View.GONE

            when (v?.id) {
                R.id.btnBack -> {
                    var intent = Intent(this@AR_fittingActivity, LikeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnBackMenu -> {
                    binding.rvMenu.visibility = View.VISIBLE
                    binding.rvTop.visibility = View.GONE
                    binding.rvPants.visibility = View.GONE
                    binding.rvDress.visibility = View.GONE
                }
                R.id.btnBackMenu2 -> {
                    binding.rvMenu.visibility = View.VISIBLE
                    binding.rvTop.visibility = View.GONE
                    binding.rvPants.visibility = View.GONE
                    binding.rvDress.visibility = View.GONE
                }
                R.id.btnBackMenu3 -> {
                    binding.rvMenu.visibility = View.VISIBLE
                    binding.rvTop.visibility = View.GONE
                    binding.rvPants.visibility = View.GONE
                    binding.rvDress.visibility = View.GONE
                }
                // 의상 뷰
                R.id.btnTops -> {
                    // 상의 출력
                    binding.rvMenu.visibility = View.GONE
                    binding.rvTop.visibility = View.VISIBLE
                    binding.rvPants.visibility = View.GONE
                    binding.rvDress.visibility = View.GONE
                }

                R.id.btnPants -> {
                    // 하의 출력
                    binding.rvMenu.visibility = View.GONE
                    binding.rvTop.visibility = View.GONE
                    binding.rvPants.visibility = View.VISIBLE
                    binding.rvDress.visibility = View.GONE
                }
                R.id.btnDress -> {
                    // 드레스 출력
                    binding.rvMenu.visibility = View.GONE
                    binding.rvTop.visibility = View.GONE
                    binding.rvPants.visibility = View.GONE
                    binding.rvDress.visibility = View.VISIBLE
                }

                R.id.btnTop1 -> {
                    binding.imgTop.setImageResource(R.drawable.top1)
                    binding.imgDress.setImageResource(R.drawable.img_trancparency)
                }
                R.id.btnTop2 -> {
                    binding.imgTop.setImageResource(R.drawable.top2)
                    binding.imgDress.setImageResource(R.drawable.img_trancparency)
                }
                R.id.btnTop3 -> {
                    binding.imgTop.setImageResource(R.drawable.top3)
                    binding.imgDress.setImageResource(R.drawable.img_trancparency)
                }

                R.id.btnPants1 -> {
                    binding.imgPants.setImageResource(R.drawable.pants1)
                    binding.imgDress.setImageResource(R.drawable.img_trancparency)
                }
                R.id.btnPants2 -> {
                    binding.imgPants.setImageResource(R.drawable.pants2)
                    binding.imgDress.setImageResource(R.drawable.img_trancparency)
                }
                R.id.btnPants3 -> {
                    binding.imgPants.setImageResource(R.drawable.pants3)
                    binding.imgDress.setImageResource(R.drawable.img_trancparency)
                }

                R.id.btnDress1 -> {
                    binding.imgTop.setImageResource(R.drawable.img_trancparency)
                    binding.imgPants.setImageResource(R.drawable.img_trancparency)
                    binding.imgDress.setImageResource(R.drawable.dress1)
                }

            }
        }
    }
}