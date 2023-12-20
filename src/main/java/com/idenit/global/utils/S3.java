package com.idenit.global.utils;

import static com.idenit.global.constants.AWS.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.global.utils
 * fileName       : S3
 * author         : Jihun Kim
 * date           : 2023-10-10
 * description    : S3
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-10        Jihun Kim       최초 생성
 */
@Component
@RequiredArgsConstructor
public class S3 {

	private final AmazonS3 amazonS3;

	/**
	 * 단일 파일 업로드
	 *
	 * @param dir  폴더
	 * @param file 파일
	 * @return 변환된 파일명
	 */
	public String upload(String dir, MultipartFile file) {

		return putS3(dir, file);
	}

	/**
	 * 다중 파일 업로드
	 *
	 * @param dir   폴더
	 * @param files 파일 목록
	 * @return 변환된 파일명 목록
	 */
	public List<String> upload(String dir, List<MultipartFile> files) {

		List<String> list = new ArrayList<>();

		files.forEach(file -> list.add(putS3(dir, file)));

		return list;
	}

	/**
	 * 파일 업로드
	 *
	 * @param dir 폴더
	 * @param file 파일
	 * @return file name
	 */
	private String putS3(String dir, MultipartFile file) {

		String name = generateFilename(file.getOriginalFilename());

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());

		try (InputStream inputStream = file.getInputStream()) {
			amazonS3.putObject(new PutObjectRequest(BUCKET + dir, name, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
		}

		return name;
	}

	/**
	 * 파일명 생성 메서드
	 *
	 * @param name 파일명
	 * @return 변환된 파일명
	 */
	private String generateFilename(String name) {

		return UUID.randomUUID().toString().concat(getFileExtension(name));
	}

	/**
	 * 파일 확장자 추출 메서드
	 *
	 * @param name 파일명
	 * @return 파일 확장자
	 */
	private String getFileExtension(String name) {
		try {
			return name.substring(name.lastIndexOf("."));
		} catch (StringIndexOutOfBoundsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + name + ") 입니다.");
		}
	}

	/**
	 * 파일 삭제
	 *
	 * @param dir  폴더
	 * @param name 파일명
	 */
	public void deleteImage(String dir, String name) {

		amazonS3.deleteObject(new DeleteObjectRequest(BUCKET + dir, name));
	}

	/**
	 * 파일 삭제
	 *
	 * @param name 파일명
	 */
	public void deleteImage(String name) {

		int index = name.lastIndexOf("/");
		String path = BUCKET + "/" + name.substring(0, index);
		String fileName = name.substring(index + 1);

		amazonS3.deleteObject(new DeleteObjectRequest(path, fileName));
	}

	/**
	 * 파일 다운로드
	 *
	 * @param dir  폴더
	 * @param name 파일명
	 * @return 다운로드
	 * @throws IOException IOException
	 */
	public ResponseEntity<byte[]> fileDownload(String dir, String name) throws IOException {

		S3Object o = amazonS3.getObject(new GetObjectRequest(BUCKET + dir, name));
		S3ObjectInputStream objectInputStream = o.getObjectContent();

		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		String fileName = URLEncoder.encode(name, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(bytes.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
	}

	/**
	 * 보안 버킷 S3 URL 생성
	 *
	 * @param path 이미지 경로
	 * @return URL
	 */
	public String generateSignedURL(String path) {

		Date expiry = new Date(System.currentTimeMillis() + 3600000);

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET, path)
			.withMethod(HttpMethod.GET)
			.withExpiration(expiry);

		URL signedURL = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

		return signedURL.toString();
	}

}
