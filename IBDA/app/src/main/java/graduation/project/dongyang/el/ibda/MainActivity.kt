package graduation.project.dongyang.el.ibda

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.Job
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //global variable
    var mSecond : Long = 10
    val mTimer = Timer()
    //

    //start : qr code scan
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        // result : 스캔된 결과
        // 내용이 없다면
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        }
        else { // 내용이 있다면
            // 1. Toast 메시지 출력.
            Toast.makeText(
                this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()

            // 2. 결과 값 TextView에 출력
            val address = result.contents.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
            startActivity(intent)
        }
    }
    //end : qr code scan

    //start : qr code activity
    private fun initQRScanner() {
        val options = ScanOptions()
        options.setPrompt("IBDA Login : 사각형 안에 QR 코드를 입력해주세요.")     // setPrompt - 하단 문구
        options.setOrientationLocked(false)     // setOrientationLocked - 가로, 세로 방향 전환
        options.setBeepEnabled(false)           // setBeepEnabled - 바코드 포착 시, Beep 소리 발생 여부 지정.
        barcodeLauncher.launch(options)
    }
    //end : qr code activity

    //start : scan button action
    private fun onScanButtonClicked(){
        initQRScanner()
    }
    //end : scan button action


    //start : onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.createQR.setOnClickListener{
            val intent=Intent(this, CreateQRActivity::class.java)
            startActivity(intent)
        }
        binding.readQR.setOnClickListener {
            onScanButtonClicked()
        }

    }
    //end onCreate

    //start : on Resume
    override fun onResume() {
        super.onResume()

        //start : timer
        mSecond=10
        val intent=Intent(this, LoadingActivity::class.java)
        // 반복적으로 사용할 TimerTask
        val mTimerTask = object : TimerTask() {
            override fun run() {
                val mHandler = Handler(Looper.getMainLooper())
                mHandler.postDelayed({
                    // 반복실행할 구문
                    mSecond--
                    if (mSecond <= 0) {
                        mTimer.cancel()
                        startActivity(intent)
                    }
                    binding.timerResult.text = "$mSecond 초 후 대기 모드로 전환됩니다."
                }, 0)
            }
        }
        mTimer.schedule(mTimerTask, 0, 1000)
    }
    //end : onResume

    //start : onStop
    override fun onStop() {
        super.onStop()
        mTimer.cancel()
    }
    //end : onStop

    //start : onPause
    override fun onPause() {
        super.onPause()
        mTimer.cancel()
    }
    //end : onPause
}
