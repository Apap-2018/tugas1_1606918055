# Tugas 1 APAP

## requirements
- spring boot
- mysql (JPA)
- thymeleaf

## features
    1  [x] [12] ​Menampilkan Data Pegawai Berdasarkan NIP
    2  [x] [12] ​Menambahkan Pegawai di Suatu Instansi
    3  [x] [12] ​Mengubah Data Pegawai
    4  [x] [12] Menampilkan Data Pegawai Berdasarkan Instansi, Provinsi dan Jabatan tertentu 
    5  [x] [7] ​ Menambahkan Jabatan    
    6  [x] [7] ​ Menampilkan Data Jabatan    
    7  [x] [7] ​ Mengubah Data Jabatan
    8  [x] [10] ​Menghapus Jabatan    
    9  [x] [5] ​ Menampilkan Daftar Jabatan
    10 [x] [6] ​ Menampilkan Pegawai Termuda dan Tertua di Setiap Instansi
    11 [x] [10] aspek sistem interaksi
    12 [x] [5]  Bonus: Menampilkan Jumlah Pegawai untuk Setiap Jabatan
                 - di fitur yang manampilkan daftar jabatan

## issues tracker
    [x] bug: url action mengubah data jabatan berubah dari idJabatan ganti jabatanId
    [x] refactor: jabatanName di form ganti jadi jabatanBeanName
    [x] bug: auto increament di hibernate gak jalan dia ga mulai dari id terakhir yang ada di db
            - solution 1:
                reset data db -> pasti bisa
                tapi data ilang
            - solution 2:
                mungkin asdos tau
    [x] todo: form untuk pegawai kurang jabatan
    [ ] todo: move all CDN css and javascript to static folder
