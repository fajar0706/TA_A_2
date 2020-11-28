package apap.ta.sipayroll.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="gaji")
public class GajiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="gaji_pokok", nullable = false)
    private String gajiPokok;

    @NotNull
    @Column(name="status_persetujuan", nullable = false)
    private String statusPersetujuan;

    @NotNull
    @Column(name="tanggal_masuk", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalMasuk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(String gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public String getStatusPersetujuan() {
        return statusPersetujuan;
    }

    public void setStatusPersetujuan(String statusPersetujuan) {
        this.statusPersetujuan = statusPersetujuan;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
}
