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

    private fun onScanButtonClicked(){
        barcodeLauncher.launch(ScanOptions())
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
