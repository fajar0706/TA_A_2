package apap.ta.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="jenisBonus")
public class JenisBonusModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name="namaBonus", nullable = false)
    private String namaBonus;

    @OneToMany(mappedBy = "jenisBonus", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<BonusModel> bonusModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBonus() {
        return namaBonus;
    }

    public void setNamaBonus(String namaBonus) {
        this.namaBonus = namaBonus;
    }

    public List<BonusModel> getBonusModel() {
        return bonusModel;
    }

    public void setBonusModel(List<BonusModel> bonusModel) {
        this.bonusModel = bonusModel;
    }
}
