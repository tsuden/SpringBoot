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
	@Table(name = "filetbl")

public class FileProperties {

		@Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    @Column(name = "file_id")
		    private Integer fileid;
		    @Column(name = "vault_code")
		    private Integer vaultcode;
		    @Column(name = "file_name")
		    private String filename;
		    @Column(name = "file_type")
		    private String filetype;
		    @Column(name = "file_format")
		    private String fileformat;
		    @Column(name = "upload_dir")
		    private String uploadDir;
			public Integer getFileid() {
				return fileid;
			}
			public void setFileid(Integer fileid) {
				this.fileid = fileid;
			}
			public Integer getVaultcode() {
				return vaultcode;
			}
			public void setVaultcode(Integer vaultcode) {
				this.vaultcode = vaultcode;
			}
			public String getFilename() {
				return filename;
			}
			public void setFilename(String filename) {
				this.filename = filename;
			}
			public String getFiletype() {
				return filetype;
			}
			public void setFiletype(String filetype) {
				this.filetype = filetype;
			}
			public String getFileformat() {
				return fileformat;
			}
			public void setFileformat(String fileformat) {
				this.fileformat = fileformat;
			}
			public String getUploadDir() {
				return uploadDir;
			}
			public void setUploadDir(String uploadDir) {
				this.uploadDir = uploadDir;
			}
		    
	}

