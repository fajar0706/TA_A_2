package apap.ta.sipayroll.rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BonusDetail {

    @JsonProperty("namaPeserta")
    private String username;

    @JsonProperty("jumlahTraining")
    private String jumlahPelatihan;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJumlahPelatihan() {
        return jumlahPelatihan;
    }

    public void setJumlahPelatihan(String jumlahPelatihan) {
        this.jumlahPelatihan = jumlahPelatihan;
    }
}
