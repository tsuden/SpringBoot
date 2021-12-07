package au.com.orawiz.vault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropertiesRepo extends JpaRepository<FileProperties, Integer>{
	 @Query("Select a from FileProperties a where user_id = ?1 and document_type = ?2")
	     FileProperties checkDocumentByUserId(Integer userId, String docType);
	     @Query("Select fileName from FileProperties a where user_id = ?1 and document_type = ?2")
	     String getUploadDocumnetPath(Integer userId, String docType);
}
