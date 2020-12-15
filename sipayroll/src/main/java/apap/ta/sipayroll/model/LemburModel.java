package apap.ta.sipayroll.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "lembur")
public class LemburModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "waktu_mulai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date waktuMulai;

    public Date getWaktuMulai() {
        return this.waktuMulai;
    }

    public void setWaktuMulai(Date waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    @NotNull
    @Column(name = "waktu_selesai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date waktuSelesai;

    public Date getWaktuSelesai() {
        return this.waktuSelesai;
    }

    public void setWaktuSelesai(Date waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    @NotNull
    @Column(name = "kompensasi_per_jam")
    private Integer kompensasiPerJam =120000;

    @NotNull
    @Column(name = "status_persetujuan")
    private Integer statusPersetujuan;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_gaji",referencedColumnName = "id")
    private GajiModel gaji;

    public GajiModel getGaji() {
        return this.gaji;
    }

    public void setGaji(GajiModel gaji) {
        this.gaji = gaji;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    

    /**
     * @return Integer return the kompensasiPerJam
     */
    public Integer getKompensasiPerJam() {
        return kompensasiPerJam;
    }

    /**
     * @param kompensasiPerJam the kompensasiPerJam to set
     */
    public void setKompensasiPerJam(Integer kompensasiPerJam) {
        this.kompensasiPerJam = kompensasiPerJam;
    }

    /**
     * @return Integer return the statusPersetujuan
     */
    public Integer getStatusPersetujuan() {
        return statusPersetujuan;
    }

    /**
     * @param statusPersetujuan the statusPersetujuan to set
     */
    public void setStatusPersetujuan(Integer statusPersetujuan) {
        this.statusPersetujuan = statusPersetujuan;
    }

   
}