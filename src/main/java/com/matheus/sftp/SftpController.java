package com.matheus.sftp;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/sftp")
public class SftpController {

    @PostMapping()
    public ResponseEntity<String> sftp() {

        try {
            FileSystemManager manager = VFS.getManager();

            FileObject local = manager.resolveFile(new ClassPathResource("upload-example.txt").getURL());
            FileObject remote = manager.resolveFile("sftp://spring:spring@172.18.0.2/upload/upload-example.txt");

            remote.copyFrom(local, Selectors.SELECT_SELF);

            local.close();
            remote.close();

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
