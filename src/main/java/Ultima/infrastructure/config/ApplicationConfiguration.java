package Ultima.infrastructure.config;

import Ultima.ComponentScanMark;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = ComponentScanMark.class)
@Import(PersistenceJpaConfiguration.class)
public class ApplicationConfiguration {
}
