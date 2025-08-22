import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etTanggalLahir: TextInputEditText
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTanggalLahir = findViewById(R.id.etTanggalLahir)

        // Setup spinner agama
        setupSpinner()

        // Setup date picker
        setupDatePicker()

        // Tombol simpan
        btnSimpan.setOnClickListener {
            if (validateForm()) {
                showBiodata()
            }
        }
    }

    private fun setupSpinner() {
        val agama = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, agama)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAgama.adapter = adapter
    }

    private fun setupDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        etTanggalLahir.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        etTanggalLahir.setText(sdf.format(calendar.time))
    }

    private fun validateForm(): Boolean {
        return when {
            etNama.text.toString().trim().isEmpty() -> {
                showToast("Nama lengkap harus diisi")
                false
            }
            etAlamat.text.toString().trim().isEmpty() -> {
                showToast("Alamat harus diisi")
                false
            }
            etTanggalLahir.text.toString().trim().isEmpty() -> {
                showToast("Tanggal lahir harus diisi")
                false
            }
            rgJenisKelamin.checkedRadioButtonId == -1 -> {
                showToast("Jenis kelamin harus dipilih")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showBiodata() {
        val nama = etNama.text.toString().trim()
        val alamat = etAlamat.text.toString().trim()
        val tanggalLahir = etTanggalLahir.text.toString().trim()
        
        val jenisKelamin = if (rbLaki.isChecked) "Laki-laki" else "Perempuan"
        val agama = spAgama.selectedItem.toString()

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("NAMA", nama)
            putExtra("ALAMAT", alamat)
            putExtra("TANGGAL_LAHIR", tanggalLahir)
            putExtra("JENIS_KELAMIN", jenisKelamin)
            putExtra("AGAMA", agama)
        }
        
        startActivity(intent)
    }
}