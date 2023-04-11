package graduation.project.dongyang.el.ibda

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

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
            binding.qrResult.text="Scan Result : \n" + result.contents.toString()

            binding.qrResult.setOnClickListener {
                val address = result.contents.toString()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
                startActivity(intent)
            }
        }
    }

    private fun initQRScanner() {
        val options = ScanOptions()
        options.setPrompt("IBDA Login : 사각형 안에 QR 코드를 입력해주세요.")     // setPrompt - 하단 문구
        options.setOrientationLocked(false)     // setOrientationLocked - 가로, 세로 방향 전환
        options.setBeepEnabled(false)           // setBeepEnabled - 바코드 포착 시, Beep 소리 발생 여부 지정.
        //options.setTimeout(8000)                // Timeout 을 지정하여 8000ms 후 종료되는 SCAN
        barcodeLauncher.launch(options)
    }

    private fun onScanButtonClicked(){
        initQRScanner()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBG.setOnClickListener{
            val intent=Intent(this, Loading::class.java)
            startActivity(intent)
        }
        binding.createQR.setOnClickListener{
            val intent=Intent(this, CreateQR::class.java)
            startActivity(intent)
        }
        binding.readQR.setOnClickListener {
            onScanButtonClicked()
        }

    }
}
