import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Ambil data dari intent
        val nama = intent.getStringExtra("NAMA") ?: ""
        val alamat = intent.getStringExtra("ALAMAT") ?: ""
        val tanggalLahir = intent.getStringExtra("TANGGAL_LAHIR") ?: ""
        val jenisKelamin = intent.getStringExtra("JENIS_KELAMIN") ?: ""
        val agama = intent.getStringExtra("AGAMA") ?: ""

        // Tampilkan data
        val hasilText = """
            Nama Lengkap: $nama
            Alamat: $alamat
            Tanggal Lahir: $tanggalLahir
            Jenis Kelamin: $jenisKelamin
            Agama: $agama
        """.trimIndent()
        
        tvHasil.text = hasilText

        // Tombol kembali
        btnKembali.setOnClickListener {
            finish()
        }
    }
}