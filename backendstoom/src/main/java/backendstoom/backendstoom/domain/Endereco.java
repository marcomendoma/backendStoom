package backendstoom.backendstoom.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


/**
 * 
 * @author Marco Antonio Mendonça
 * 
 * Endereco	- Representa a entidade de endereco no banco de dados.		
 *
 */

@Entity
@Table(name="Endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	private Long id;
	
	@NotEmpty(message = "Campo streetName é obrigatório.")
	@Column(name = "streetName", length=50, nullable=false)
	private String streetName;
	
	@NotEmpty(message = "Campo number é obrigatório.")
	@Column(name = "number", nullable=false)
	private Long number;
	
	private String complement;
	
	@NotEmpty(message = "Campo neighbourhood é obrigatório.")
	@Column(name = "neighbourhood", length=50, nullable=false)
	private String neighbourhood;
	
	@NotEmpty(message = "Campo city é obrigatório.")
	@Column(name = "city", length=50, nullable=false)
	private String city;
	
	@NotEmpty(message = "Campo state é obrigatório.")
	@Column(name = "state", length=50, nullable=false)
	private String state;
	
	@NotEmpty(message = "Campo country é obrigatório.")
	@Column(name = "country", length=30, nullable=false)
	private String country;
	
	@NotEmpty(message = "Campo zipcode é obrigatório.")
	@Column(name = "zipcode", nullable=false)
	private Long zipcode;
	
	private Long latitude;
	private Long longitude;
	
	public Endereco () {}

	public Endereco(String streetName, Long number, String complement, String neighbourhood, String city, String state,
			String country, Long zipcode, Long latitude, Long longitude) {
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", streetName=" + streetName + ", number=" + number + ", complement=" + complement
				+ ", neighbourhood=" + neighbourhood + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", zipcode=" + zipcode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
