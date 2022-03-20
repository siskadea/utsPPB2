package com.siskadea.utsku

class data_member {

    //Deklarasi Variable
    var nama: String? = null
    var jenis_kelamin: String? = null
    var nohp: String? = null
    var alamat: String? = null
    var key: String? = null
    //Membuat Konstuktor kosong untuk membaca data snapshot
    constructor() {}
    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User

    constructor(nama: String?, jenis_kelamin: String?, nohp: String?, alamat: String?) {
        this.nama = nama
        this.jenis_kelamin = jenis_kelamin
        this.nohp = nohp
        this.alamat = alamat
    }
}