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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_penyetuju",referencedColumnName = "id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userPenyetuju;

    @OneToOne
    @JoinColumn(name = "uuid_user",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @OneToMany(mappedBy="gaji",fetch=FetchType.LAZY,cascade= CascadeType.ALL)
    private List<LemburModel> listLembur;

    

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

    public void setStatusPersetujuan(int statusPersetujuan) {
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
    public List<LemburModel> getListLembur() {
        return this.listLembur;
    }

    public void setListLembur(List<LemburModel> listLembur) {
        this.listLembur = listLembur;
    }
}
