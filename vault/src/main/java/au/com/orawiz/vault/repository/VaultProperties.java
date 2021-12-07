package au.com.orawiz.vault.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "vaulttbl")
public class VaultProperties {

	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "vault_id")
	    private Integer vaultid;
	    @Column(name = "vault_code")
	    private Integer vaultcode;
	    @Column(name = "vault_name")
	    private String vaultname;
	    @Column(name = "vault_loc")
	    private String vaultloc;
		public Integer getVaultid() {
			return vaultid;
		}
		public void setVaultid(Integer vaultid) {
			this.vaultid = vaultid;
		}
		public Integer getVaultcode() {
			return vaultcode;
		}
		public void setVaultcode(Integer vaultcode) {
			this.vaultcode = vaultcode;
		}
		public String getVaultname() {
			return vaultname;
		}
		public void setVaultname(String vaultname) {
			this.vaultname = vaultname;
		}
		public String getVaultloc() {
			return vaultloc;
		}
		public void setVaultloc(String vaultloc) {
			this.vaultloc = vaultloc;
		}
}
