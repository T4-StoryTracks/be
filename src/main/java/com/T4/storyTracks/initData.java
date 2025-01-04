package com.T4.storyTracks;//package com.T4.storyTracks;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.TimeZone;
//
//@Component
//@RequiredArgsConstructor
//public class initData implements ApplicationListener<ApplicationReadyEvent> {
//
//    private final EmpRepository repository;
//
//    private final DeptRepository deptRepository;
//    private final PasswordEncoder passwordEncoder; //시큐리티 통과용 비밀번호 복호화
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        //배포 서버 JVM 시간 설정
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//
//        // 초기 부서 데이터
//        List<Department> depts = new ArrayList<>();
//        depts.add(new Department(1, "사장실","boss", "010-1111-1111"));
//        depts.add(new Department(2, "재무부","fina", "010-2222-2222"));
//        depts.add(new Department(3, "총무부","general", "010-3333-3333"));
//        depts.add(new Department(4, "물류유통부","logistic", "010-4444-4444"));
//        depts.add(new Department(5, "인사부","hr", "010-5555-5555"));
//
//        deptRepository.saveAllAndFlush(depts);
//
//        // 초기 사원 데이터
//        List<Employee> emps = new ArrayList<>();
//        emps.add(Employee.builder().empId(1L).empName("최필립").birth(LocalDate.parse("1985-11-05"))
//                .joinDate(LocalDate.parse("2017-12-25")).email("lib@monster.com").dept(depts.get(0))
//                .gender("man").position(Position.ROLE_DEPTLEADER).pwd(passwordEncoder.encode("1")).status(false).postNo("08374").addr("서울 구로구 오리로 1063-2")
//                .dAddr("길동빌딩 302호").empTel("010-2344-2345").build());
//        emps.add(Employee.builder().empId(2L).empName("배수지").birth(LocalDate.parse("1991-01-25"))
//                .joinDate(LocalDate.parse("2013-12-25")).email("suzi@monster.com").dept(depts.get(1))
//                .gender("woman").position(Position.ROLE_EMPLOYEE).pwd(passwordEncoder.encode("1")).status(false).postNo("056936").addr("경기 남양주시 오남읍 양지로대대울1길 4")
//                .dAddr("오리오피스텔 1102호").empTel("010-2644-3457").build());
//        emps.add(Employee.builder().empId(3L).empName("이두나").birth(LocalDate.parse("1995-05-30"))
//                .joinDate(LocalDate.parse("2015-12-25")).email("duna@monster.com").dept(depts.get(2))
//                .gender("woman").position(Position.ROLE_TEAMLEADER).pwd(passwordEncoder.encode("1")).status(false).postNo("08853").addr("서울 서초구 과천대로 786")
//                .dAddr("두나팰리스 A동 1023호").empTel("010-1246-2241").build());
//        emps.add(Employee.builder().empId(4L).empName("김길동").birth(LocalDate.parse("1999-04-19"))
//                .joinDate(LocalDate.parse("2021-12-25")).email("kimgil@monster.com").dept(depts.get(3))
//                .gender("man").position(Position.ROLE_EMPLOYEE).pwd(passwordEncoder.encode("1")).status(false).postNo("02866").addr("서울 강서구 강서로 375-7")
//                .dAddr("푸르지오 2차 305동 201호").empTel("010-6789-1384").build());
//        emps.add(Employee.builder().empId(5L).empName("최사원").birth(LocalDate.parse("1996-12-02"))
//                .joinDate(LocalDate.parse("2022-12-25")).email("sawon@monster.com").dept(depts.get(4))
//                .gender("man").position(Position.ROLE_EMPLOYEE).pwd(passwordEncoder.encode("1")).status(false).postNo("07316").addr("부산 강서구 가락대로 197-1")
//                .dAddr("오리빌라 102호").empTel("010-2347-0663").build());
//
//        repository.saveAllAndFlush(emps);
//
//        // 점검용 사원
//        Employee specificEmp = new Employee();
//        specificEmp.setEmpId(11L); // empId를 11로 설정
//        specificEmp.setStatus(false);
//        specificEmp.setBirth(LocalDate.parse("2023-12-20"));
//        specificEmp.setEmpTel("010-9999-9990");
//        specificEmp.setGender("man");
//        specificEmp.setEmail("lhg0529@gmail.com");
//        specificEmp.setAddr("수원시");
//        specificEmp.setEmpName("이현기");
//        specificEmp.setPostNo("00025");
//        specificEmp.setDAddr("권선구");
//        specificEmp.setPosition(Position.ROLE_STOREMANAGER);
//        specificEmp.setJoinDate(LocalDate.parse("2022-12-20"));
//        specificEmp.setDept(deptRepository.findById(4).orElse(null));
//        specificEmp.setPwd(passwordEncoder.encode("1q")); // 비밀번호를 "1q"로 설정
//        repository.saveAndFlush(specificEmp);
//
//
//        // 초기 채팅 데이터
//        // 사번이 1인 사원과 11인 사원을 가져옵니다.
//        Employee emp1 = repository.findById(1L).orElse(null);
//        Employee emp2 = repository.findById(2L).orElse(null);
//        Employee emp11 = repository.findById(11L).orElse(null);
//    }
//}
