package com.dmz.global.config;

import static com.dmz.global.constants.AWS.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * packageName    : com.idenit.global.config
 * fileName       : AWSConfig
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */

@Configuration
public class AWSConfig {

	@Bean
	public AWSCredentials awsCredentials() {

		return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
	}

	@Bean
	public AmazonS3Client amazonS3Client() {

		return (AmazonS3Client)AmazonS3ClientBuilder.standard()
			.withRegion(REGION)
			.withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
			.build();
	}

}
