package com.idenit.global.config.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

/**
 * packageName    : com.kasko.global.converter
 * fileName       : JsonCustomConfig
 * author         : Hyuk Kim
 * date           : 2023-11-14
 * description    : null 값인 Json 데이터를 "" 으로 치환해주는 Bean 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-14        Hyuk Kim       최초 생성
 */
@Configuration
public class JsonCustomConfig extends JsonSerializer<Object> {

    @Bean
    public MappingJackson2HttpMessageConverter httpMessageConverter(ObjectMapper objectMapper){
        objectMapper.getSerializerProvider()
                .setNullValueSerializer(this);

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeString("");
    }

}
