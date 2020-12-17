package apap.ta.sipayroll.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PesertaDetail {

    @JsonProperty("id")
    private String id;

  public String getId() {
  	return this.id;
  }
  public void setId(String id) {
  	this.id = id;
  }



    @JsonProperty("nama")
    private String nama;

public String getNama() {
	return this.nama;
}
public void setNama(String nama) {
	this.nama = nama;
}


   

    @JsonProperty("nomor")
    private String nomor;

public String getNomor() {
	return this.nomor;
}
public void setNomor(String nomor) {
	this.nomor = nomor;
}


    
    @JsonProperty("departemen")
    private String departemen;

public String getDepartemen() {
	return this.departemen;
}
public void setDepartemen(String departemen) {
	this.departemen = departemen;
}


    




}
