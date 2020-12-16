package apap.ta.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="gaji")
public class GajiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="gaji_pokok", nullable = false)
    private Integer gajiPokok;

    @NotNull
    @Column(name="status_persetujuan", nullable = false)
    private Integer statusPersetujuan;

    @NotNull
    @Column(name="tanggal_masuk", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalMasuk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_pengaju",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userPengaju;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "uuid_penyetuju",referencedColumnName = "id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userPenyetuju;

    @OneToOne
    @JoinColumn(name = "uuid_user",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @OneToMany(mappedBy = "gaji", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<LemburModel> lemburList;


    @OneToMany(mappedBy = "gaji", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<BonusModel> bonusList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Integer gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public Integer getStatusPersetujuan() {
        return statusPersetujuan;
    }

    public void setStatusPersetujuan(Integer statusPersetujuan) {
        this.statusPersetujuan = statusPersetujuan;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public UserModel getUserPengaju() {
        return userPengaju;
    }

    public void setUserPengaju(UserModel userPengaju) {
        this.userPengaju = userPengaju;
    }

    public UserModel getUserPenyetuju() {
        return userPenyetuju;
    }

    public void setUserPenyetuju(UserModel userPenyetuju) {
        this.userPenyetuju = userPenyetuju;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<LemburModel> getLemburList() {
        return lemburList;
    }

    public void setLemburList(List<LemburModel> lemburList) {
        this.lemburList = lemburList;
    }

    public List<BonusModel> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<BonusModel> bonusList) {
        this.bonusList = bonusList;
    }
}
