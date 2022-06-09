package com.qu.gradproject.service;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qu.gradproject.dto.AttendanceDTO;
import com.qu.gradproject.entity.AttendanceEntity;
import com.qu.gradproject.entity.CodeCheckEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.CodeCheckRepository;
import com.qu.gradproject.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	CodeCheckRepository codeCheckRepository;
	@Autowired
	AttendanceRepository attendanceRepository;
	@Autowired
	GroupsService groupsService;
	@Autowired
	UserService userService;
	
	public static String alphaNumericString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}
	
	
	public void newAttendanceSession(Long groupId) {
		String code=alphaNumericString(5);
	 
	    LocalDate.now();
	    
		CodeCheckEntity codeCheckEntity=new CodeCheckEntity();
		GroupsEntity groupsEntity=groupsService.getGroupEntityById(groupId);
		
		codeCheckEntity.setAttendanceCode(code);
		codeCheckEntity.setDate(LocalDate.now());
		codeCheckEntity.setGroupsEntity(groupsEntity);
		 codeCheckRepository.save(codeCheckEntity);
		TimerTask timerTask = new TimerTask() {
	           @Override
	           public void run() {
	               codeCheckRepository.delete(codeCheckEntity);
	           }

	       };
	       Timer timer = new Timer();
	   
	      timer.schedule(timerTask, 120000);
	     for(UserEntity users: groupsEntity.getUserEntities()) {
	    	 if(users.getRole().contains("Instructor")) break;
	      AttendanceEntity attendanceEntity=new AttendanceEntity();
	      attendanceEntity.setGroupsEntity(groupsEntity);
	      attendanceEntity.setUserEntity(users);
	      attendanceEntity.setStatus("absent");
	      attendanceEntity.setDate(LocalDate.now());
	      attendanceRepository.save(attendanceEntity);
	      
	     }
	   }
		
	public void takeAttendance(UserEntity userEntity, String code,Long groupId) {
		if(codeCheckRepository.findByAttendanceCode(code)!=null) {
			attendanceRepository.deleteByUserEntityAndDate(userEntity,LocalDate.now(),groupsService.getGroupEntityById(groupId));
		}
		
		
	}
	public CodeCheckEntity getCodeById(Long id) {
		Optional<CodeCheckEntity>optional=codeCheckRepository.findById(id);
		CodeCheckEntity codeCheckEntity=optional.orElseThrow(RuntimeException::new);
		return codeCheckEntity;
		
	}
	
	public CodeCheckEntity getCodeByGroup(Long id) {
		
		return codeCheckRepository.findCurrentAttendanceCode(groupsService.getGroupEntityById(id),LocalDate.now());
		
	}
	
	public Boolean checkIfAttendanceTaken(UserEntity userEntity,GroupsEntity groupEntity) {
		
		if(attendanceRepository.checkIfAttendanceTaken(userEntity,groupEntity,LocalDate.now())!=null) {
			return false;
		}
		
		return true;
	}
	
	
	public List<LocalDate> getAllPreviosAttendance(Long id){
		
	return	attendanceRepository.findAllDatesByGroups(groupsService.getGroupEntityById(id));
		
	}
	
	public List<AttendanceEntity> getAllAbsentStudent(Long id,String dateOflecture){
		GroupsEntity groupsEntity=groupsService.getGroupEntityById(id);
		return attendanceRepository.getAllAbsentStudents(groupsEntity, LocalDate.now());
	}
	
	
	public List<AttendanceDTO>getAttendanceByDate(Long groupId,String DateOfLecture){
		List<AttendanceDTO>attendance=new ArrayList<>();
		List<AttendanceEntity> absentStudents=getAllAbsentStudent(groupId, DateOfLecture);
		List<UserEntity> AllStudents=userService.getStudentsByGroupId(groupId);
		for(UserEntity student: AllStudents) {
			AttendanceDTO att=new AttendanceDTO();
			att.setStatus("present");
			att.setDateOfLecture(DateOfLecture);
			att.setName(student.getName());
			att.setId(student.getId());
			
			for(AttendanceEntity absents:absentStudents) {
				if(absents.getUserEntity()==student) {
					att.setStatus("absent");
				}
			}
			
			attendance.add(att);
		}
		return attendance;
	}
	
	public void changeAttendanceToPresent(Long userId,Long groupId,String date) {
		
		attendanceRepository.deleteByUserEntityAndDate(userService.getUserEntityById(userId), LocalDate.parse(date), groupsService.getGroupEntityById(groupId));
		
	}
	
	public void changeAttendanceToAbsent(Long userId,Long groupId,String date) {
		AttendanceEntity attendanceEntity=new AttendanceEntity();
		attendanceEntity.setDate(LocalDate.parse(date));
		attendanceEntity.setGroupsEntity(groupsService.getGroupEntityById(groupId));
		attendanceEntity.setStatus("absent");
		attendanceEntity.setUserEntity(userService.getUserEntityById(userId));
		
		attendanceRepository.save(attendanceEntity);
		
		
	}
	
		
	}

