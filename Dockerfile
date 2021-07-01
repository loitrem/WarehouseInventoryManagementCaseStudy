FROM openjdk:11
ADD target/CaseStudy.jar CaseStudy.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "CaseStudy.jar"]