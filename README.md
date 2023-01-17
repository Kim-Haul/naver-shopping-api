## RDS MySQL 세팅 및 EC2 연결

```java
// RDBMS 연결(AWS 이용)
1. RDS(MySQL)구매하기
2. RDS 포트 열어주기
3. intelliJ에서 확인하기
4. 스프링 부트를 MySQL과 연결하기
  spring.datasource.url=jdbc:mysql://나의엔드포인트:3306/RDS이름
  spring.datasource.username=나의USERNAME
  spring.datasource.password=나의패스워드
  spring.jpa.hibernate.ddl-auto=update
  
// 배포 준비
1. EC2 구매하기
2. 배포 파일 빌드하기
3. git Bash 에서 서버 접속 후, 서버에 OpenJDK 설치하기
  ssh -i `${keypair file}` ubuntu@`${EC2 IP}`
  sudo apt-get update
  sudo apt-get install openjdk-17-jdk
  java -version
4. Filezilla를 이용해 배포 파일 업로드하기
   NEW site - EC2 연결 - 프로토콜(SFTP) - 호스트(EC2 IP) - 로그온 유형(키파일)
5. 스프링 부트 작동시키기
   git Bash 에서 서버 접속 후, java -jar `${JAR 파일명}`.jar
6. AWS에서 80, 8080 포트 열어주기
   EC2는 기본적으로 22번 포트만 열어둠. **80은 기본 포트**, 8080은 우리가 확인하기 위한 포트
   
// 포트포워딩 & nohup 적용
1. (git Bash) ctrl + c 기존 서비스 종료
2. sudo iptables -t nat -A PREROUTING -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
3. 서비스 재시작 java -jar `${JAR 파일명}`.jar
4. SSH 접속을 끊어도 서버가 계속 돌게하기 
   nohup java -jar `${JAR 파일명}`.jar &
5. nohup으로 설정한 서버 종료
   ps -ef | grep java
   kill -9 ${바로 위 명령어 입력시 ubuntu 뒤에 나오는 숫자}
   ps -ef | grep java
   
// 도메인 붙이기
1. 가비아 - DNS 정보 - 도메인 연결(설정) - 설정 - 레코드 수정 - 레코드 추가 - 타입(A)&호스트(@)&값(EC2 IP)
```
