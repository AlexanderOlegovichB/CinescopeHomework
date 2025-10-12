package junit;


import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({SelenideExtension.class, LoginExtension.class})
public @interface UITest {
    String browser() default "chrome";
    boolean headless() default false;
}
