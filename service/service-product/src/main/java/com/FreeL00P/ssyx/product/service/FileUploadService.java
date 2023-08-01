package com.FreeL00P.ssyx.product.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileUploadService
 *
 * @author fj
 * @since 2023/8/1 14:00
 */
public interface FileUploadService {
    String fileUpload(MultipartFile file);
}
