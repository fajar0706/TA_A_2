package apap.ta.sipayroll.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="bonus")
public class BonusModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="jumlah_bonus", nullable = false)
    private Integer jumlahBonus;

    @NotNull
    @Column(name="tanggal_diberikan", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalDiberikan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jenisBonus", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisBonusModel jenisBonus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gaji", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GajiModel gaji;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJumlahBonus() {
        return jumlahBonus;
    }

    public void setJumlahBonus(Integer jumlahBonus) {
        this.jumlahBonus = jumlahBonus;
    }

    public LocalDate getTanggalDiberikan() {
        return tanggalDiberikan;
    }

    public void setTanggalDiberikan(LocalDate tanggalDiberikan) {
        this.tanggalDiberikan = tanggalDiberikan;
    }

    public JenisBonusModel getJenisBonus() {
        return jenisBonus;
    }

    public void setJenisBonus(JenisBonusModel jenisBonus) {
        this.jenisBonus = jenisBonus;
    }

    public GajiModel getGaji() {
        return gaji;
    }

    public void setGaji(GajiModel gaji) {
        this.gaji = gaji;
    }
}
