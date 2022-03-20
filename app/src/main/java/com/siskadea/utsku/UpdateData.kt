package com.siskadea.utsku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_data.*


class UpdateData : AppCompatActivity() {
    //Deklarasi Variable
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekNama: String? = null
    private var cekJk: String? = null
    private var cekNohp: String? = null
    private var cekAlamat: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        supportActionBar!!.title = "Update Data"
//Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        data //memanggil method "data"
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//Mendapatkan Data Mahasiswa yang akan dicek
                cekNama = new_nama.getText().toString()
                cekJk = new_jk.getText().toString()
                cekNohp = new_nohp.getText().toString()
                cekAlamat = new_alamat.getText().toString()
//Mengecek agar tidak ada data yang kosong, saat proses update
                if (isEmpty(cekNama!!) || isEmpty(cekJk!!) ||
                    isEmpty(cekNohp!!) ||
                    isEmpty(cekAlamat!!)) {
                    Toast.makeText(
                        this@UpdateData,
                        "Data tidak boleh ada yang kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
/*Menjalankan proses update data.
Method Setter digunakan untuk mendapakan data baru yang diinputkan User.*/
                    val setMahasiswa = data_member()
                    setMahasiswa.nama = new_nama.getText().toString()
                    setMahasiswa.jenis_kelamin = new_jk.getText().toString()
                    setMahasiswa.nohp = new_nohp.getText().toString()
                    setMahasiswa.alamat = new_alamat.getText().toString()
                    updateMahasiswa(setMahasiswa)
                }
            }
        })
    }
    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }
    //Menampilkan data yang akan di update
    private val data: Unit
        private get() {
//Menampilkan data dari item yang dipilih sebelumnya
            val getNama = intent.extras!!.getString("dataNama")
            val getJk = intent.extras!!.getString("dataJk")
            val getNohp = intent.extras!!.getString("dataNohp")
            val getAlamat = intent.extras!!.getString("dataAlamat")
            new_nama!!.setText(getNama)
            new_jk!!.setText(getJk)
            new_nohp!!.setText(getNohp)
            new_alamat!!.setText(getAlamat)
        }
    //Proses Update data yang sudah ditentukan
    private fun updateMahasiswa(mahasiswa: data_member) {
        val userID = auth!!.uid
        val getKey = intent.extras!!.getString("getPrimaryKey")
        database!!.child("Admin")
            .child(userID!!)
            .child("Mahasiswa")
            .child(getKey!!)
            .setValue(mahasiswa)
            .addOnSuccessListener {
                new_nama!!.setText("")
                new_jk!!.setText("")
                new_nohp!!.setText("")
                new_alamat!!.setText("")
                Toast.makeText(this@UpdateData, "Data Berhasil diubah",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
    }
}