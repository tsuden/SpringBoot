package au.com.orawiz.vault.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import au.com.orawiz.vault.messages.DocumentStorageException;
import au.com.orawiz.vault.repository.FileProperties;
import au.com.orawiz.vault.repository.PropertiesRepo;
import au.com.orawiz.vault.services.FilesStorageService;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  private final Path fileStorageLocation;
  @Autowired
  PropertiesRepo docStorageRepo;

  @Autowired
  public FilesStorageService(FileProperties fileStorageProperties) {
      this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
              .toAbsolutePath().normalize();
   try {
          Files.createDirectories(this.fileStorageLocation);
      } catch (Exception ex) {
        throw new DocumentStorageException("Could not create the directory where the uploaded files will be stored.", ex);
      }
  }

  public String storeFile(MultipartFile file, Integer fileId, String fileType) {
      // Normalize file name
      String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
      String fileName = "";

      try {
          // Check if the file's name contains invalid characters
          if(originalFileName.contains("..")) {
              throw new DocumentStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
          }

          String fileExtension = "";
          try {
          fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
          } catch(Exception e) {
              fileExtension = "";
          }
          fileName = fileId + "_" + fileType + fileExtension;
       // Copy file to the target location (Replacing existing file with the same name)
          Path targetLocation = this.fileStorageLocation.resolve(fileName);
          Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
          
          FileProperties doc = docStorageRepo.checkDocumentByUserId(fileid, fileType);
          if(doc != null) {
              doc.setFileformat(file.getContentType());
             doc.setFilename(fileName);
              docStorageRepo.save(doc);

          } else {
        	  FileProperties newDoc = new FileProperties();
              newDoc.setFileid(fileId);
             newDoc.setFileformat(file.getContentType());
              newDoc.setFilename(fileName);
              newDoc.setFileformat(fileType);
              docStorageRepo.save(newDoc);
          }

          return fileName;
      } catch (IOException ex) {
          throw new DocumentStorageException("Could not store file " + fileName + ". Please try again!", ex);
      }
  }

  public Resource loadFileAsResource(String fileName) throws Exception {
      try {
          Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
          Resource resource = new UrlResource(filePath.toUri());
          if(resource.exists()) {
              return resource;
          } else {
              throw new FileNotFoundException("File not found " + fileName);
          }
      } catch (MalformedURLException ex) {
          throw new FileNotFoundException("File not found " + fileName);
      }
  }

  public String getDocumentName(Integer userId, String docType) {
      return docStorageRepo.getUploadDocumnetPath(userId, docType);
      
  }
}