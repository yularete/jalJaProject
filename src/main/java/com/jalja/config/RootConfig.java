package com.jalja.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정 클래스임을 명시
public class RootConfig {

    @Bean //@Bean을 이용해 ModelMapper를 스프링의 빈으로 설정
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
                //가장 엄격한 전략, source와 destination의 타입과 필드명이 같을 때만 변환,의도하지 않은 매핑이 일어나는 것을 방지할 때 사용

        return modelMapper;
    }
}
