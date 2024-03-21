package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    /*
     * 컨버터를 사용하든, 포맷터를 사용하든 등록 방법은 다르지만, 사용할 때는 컨버전 서비스를 통해서 일관성 있게 사용할 수 있다.
     * 주의!
     * 메시지 컨버터( HttpMessageConverter )에는 컨버전 서비스가 적용되지 않는다.
     * 메시지 컨버터 (HttpMessageConverter)의 역할
     * 1. HTTP 메시지 바디 ==> 객체
     * 2. 객체 ==> HTTP 메시지 바디 : 주의!! **JSON 포맷 변경 시** 해당 라이브러리가 제공하는 설정을 통해서 포맷 지정 필요  == 컨버전 서비스와 전혀 관계 없음
     */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override       // 스프링은 내부에서 ConversionService 를 사용해서 타입을 변환 ( FormatterRegistry 상속 )
    public void addFormatters(FormatterRegistry registry) {
        //주석처리 우선순위 - Converter가 우선순위 높음
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 인터페이스 분리 원칙 - ISP(Interface Segregation Principle) -> 스프링은 내부에서 ConversionService 를 제공

        //추가
        registry.addFormatter(new MyNumberFormatter());


    }
}
